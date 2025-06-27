package com.smartechgroup.e_commerce.service;


import com.smartechgroup.e_commerce.model.Order;
import com.smartechgroup.e_commerce.model.Product;
import com.smartechgroup.e_commerce.model.ShippingAddress;
import com.smartechgroup.e_commerce.repository.OrderRepository;
import com.smartechgroup.e_commerce.response.OrderItemResponse;
import com.smartechgroup.e_commerce.response.OrderResponse;
import com.smartechgroup.e_commerce.response.ProductOrderResponse;
import com.smartechgroup.e_commerce.response.ShippingAddressResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setPaymentStatus(order.getPaymentStatus());
        response.setSubtotal(order.getSubtotal());
        response.setTax(order.getTax());
        response.setShipping(order.getShipping());
        response.setTotal(order.getTotal());

        // Map Shipping Address
        ShippingAddressResponse shippingAddressResponse = new ShippingAddressResponse();
        ShippingAddress sa = order.getShippingAddress();
        shippingAddressResponse.setFullName(sa.getFullName());
        shippingAddressResponse.setEmail(sa.getEmail());
        shippingAddressResponse.setPhone(sa.getPhone());
        shippingAddressResponse.setAddress(sa.getAddress());
        shippingAddressResponse.setCity(sa.getCity());
        shippingAddressResponse.setProvince(sa.getProvince());
        response.setShippingAddress(shippingAddressResponse);

        // Map Items
        List<OrderItemResponse> itemResponses = order.getItems().stream().map(item -> {
            OrderItemResponse itemResp = new OrderItemResponse();
            itemResp.setQuantity(item.getQuantity());

            Product product = item.getProduct();
            var productResp = new ProductOrderResponse();
            productResp.setId(product.getId());
            productResp.setName(product.getName());
            productResp.setPrice(product.getPrice());
            productResp.setImage(product.getImage()); // make sure `image` exists

            itemResp.setProduct(productResp);
            return itemResp;
        }).toList();

        response.setItems(itemResponses);
        return response;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUserId(userId);

        return orders.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            response.setId(order.getId());
            response.setPaymentMethod(order.getPaymentMethod());
            response.setPaymentStatus(order.getPaymentStatus());
            response.setSubtotal(order.getSubtotal());
            response.setTax(order.getTax());
            response.setShipping(order.getShipping());
            response.setTotal(order.getTotal());

            // Map Shipping Address
            ShippingAddress sa = order.getShippingAddress();
            ShippingAddressResponse shippingAddressResponse = new ShippingAddressResponse();
            shippingAddressResponse.setFullName(sa.getFullName());
            shippingAddressResponse.setEmail(sa.getEmail());
            shippingAddressResponse.setPhone(sa.getPhone());
            shippingAddressResponse.setAddress(sa.getAddress());
            shippingAddressResponse.setCity(sa.getCity());
            shippingAddressResponse.setProvince(sa.getProvince());
            response.setShippingAddress(shippingAddressResponse);

            // Map Items
            List<OrderItemResponse> itemResponses = order.getItems().stream().map(item -> {
                OrderItemResponse itemResp = new OrderItemResponse();
                itemResp.setQuantity(item.getQuantity());

                Product product = item.getProduct();
                ProductOrderResponse productResp = new ProductOrderResponse();
                productResp.setId(product.getId());
                productResp.setName(product.getName());
                productResp.setPrice(product.getPrice());
                productResp.setImage(product.getImage());

                itemResp.setProduct(productResp);
                return itemResp;
            }).toList();

            response.setItems(itemResponses);
            return response;
        }).toList();
    }

    @Override
    public List<Order> getOrdersByCustomerNotified(Boolean customerNotified) {
        return orderRepository.findOrdersByCustomerNotified(customerNotified);
    }


}
