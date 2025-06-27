package com.smartechgroup.e_commerce.service;

import com.smartechgroup.e_commerce.api_service.ApiPaymentService;
import com.smartechgroup.e_commerce.api_service.InvoiceItem;
import com.smartechgroup.e_commerce.enums.MobileMoneyMethod;
import com.smartechgroup.e_commerce.model.*;
import com.smartechgroup.e_commerce.api_service.ApiPaymentRequest;
import com.smartechgroup.e_commerce.repository.PaymentRepository;
import com.smartechgroup.e_commerce.repository.ProductRepository;
import com.smartechgroup.e_commerce.request.PaymentItem;
import com.smartechgroup.e_commerce.request.PaymentRequest;
import com.smartechgroup.e_commerce.request.ShippingAddressRequest;
import com.smartechgroup.e_commerce.response.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final ApiPaymentService apiPaymentService;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(ApiPaymentService apiPaymentService, ProductRepository productRepository, PaymentRepository paymentRepository) {
        this.apiPaymentService = apiPaymentService;
        this.productRepository = productRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Response processPayment(PaymentRequest request) throws Exception {
        var response = new Response();

        if (request.getItems().isEmpty()) {
            response.setStatusCode(400);
            response.setMessage("No items to pay");
            return response;
        }

        var apiPaymentRequest = new ApiPaymentRequest();
        apiPaymentRequest.setReference(generateReference());
        apiPaymentRequest.setInvoiceNo(generateInvoiceNumber());
        apiPaymentRequest.setMobileNo(request.getEcocashNumber());
        apiPaymentRequest.setMobileMoneyMethod(MobileMoneyMethod.ECOCASH);

        List<InvoiceItem> invoiceItems = request.getItems().stream().map(item -> {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(item.getProductId());
            invoiceItem.setAmount(item.getQuantity() * item.getPrice());
            return invoiceItem;
        }).collect(Collectors.toList());
        apiPaymentRequest.setItems(invoiceItems);

        var paymentApiResponse = apiPaymentService.initiatePayment(apiPaymentRequest);

        Payment payment = Payment.builder()
                .paymentMethod(apiPaymentRequest.getMobileMoneyMethod().name())
                .ecocashNumber(request.getEcocashNumber())
                .subtotal(request.getSubtotal())
                .tax(request.getTax())
                .shipping(request.getShipping())
                .total(request.getTotal())
                .paymentStatus(paymentApiResponse.getStatusCode() == 200 ? "PAID" : "FAILED")
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        for (PaymentItem item : request.getItems()) {
            productRepository.findById(Long.valueOf(item.getProductId())).ifPresent(product -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setPrice(product.getPrice());
                orderItem.setQuantity(item.getQuantity());
                orderItems.add(orderItem);
            });
        }

        ShippingAddressRequest saReq = request.getShippingAddress();
        ShippingAddress shippingAddress = ShippingAddress.builder()
                .fullName(saReq.getFullName())
                .email(saReq.getEmail())
                .phone(saReq.getPhone())
                .address(saReq.getAddress())
                .city(saReq.getCity())
                .province(saReq.getProvince())
                .build();

        Order order = Order.builder()
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .subtotal(payment.getSubtotal())
                .tax(payment.getTax())
                .shipping(payment.getShipping())
                .total(payment.getTotal())
                .shippingAddress(shippingAddress)
                .items(orderItems)
                .payment(payment)
                .userId(request.getUserId())
                .customerNotified(Boolean.FALSE)
                .build();

        shippingAddress.setOrder(order);
        orderItems.forEach(item -> item.setOrder(order));
        payment.setOrder(order);
        paymentRepository.save(payment);

        response.setStatusCode(paymentApiResponse.getStatusCode());
        response.setMessage(paymentApiResponse.getMessage());
        response.setData(Map.of(
                "orderId", order.getId(),
                "status", payment.getPaymentStatus()
        ));

        return response;
    }


    public String generateReference() {
        int randomNum = (int) (Math.random() * 90000) + 10000;
        return "REF" + randomNum;
    }

    public String generateInvoiceNumber() {
        int randomNum = (int) (Math.random() * 9000) + 1000;
        return "INV" + randomNum;
    }

}
