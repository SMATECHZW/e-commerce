package com.smartechgroup.e_commerce.util;

import com.smartechgroup.e_commerce.api_service.EmailNotificationRequest;
import com.smartechgroup.e_commerce.api_service.EmailNotificationService;
import com.smartechgroup.e_commerce.model.Order;
import com.smartechgroup.e_commerce.repository.OrderRepository;
import com.smartechgroup.e_commerce.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderWorker {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final EmailNotificationService emailNotificationService;

    public OrderWorker(OrderService orderService, OrderRepository orderRepository, EmailNotificationService emailNotificationService) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
    }

    @Scheduled(fixedRate = 1000000)
    public void sendEmail() {
        log.info("Checking for orders to notify...");

        List<Order> orders = orderService.getOrdersByCustomerNotified(false);
        if (orders.isEmpty()) {
            log.info("No orders found for notification.");
            return;
        }

        notifyCustomers(orders);
    }

    public void notifyCustomers(List<Order> orders) {
        for (Order order : orders) {
            String message = buildOrderConfirmationEmail(order);

            EmailNotificationRequest emailRequest = new EmailNotificationRequest();
            emailRequest.setTo(order.getShippingAddress().getEmail());
            emailRequest.setSubject("Order Confirmation");
            emailRequest.setMessage(message);

            emailNotificationService.sendEmail(emailRequest);

            order.setCustomerNotified(true);
            orderRepository.save(order);
        }
    }

    public String buildOrderConfirmationEmail(Order order) {
        var itemList = new StringBuilder();

        itemList.append("Here are the details of your order:\n\n");
        itemList.append(String.format("%-30s %-10s\n", "Item", "Price (USD)"));
        itemList.append("---------------------------------------------\n");

        order.getItems().forEach(item -> {
            itemList.append(String.format("%-30s $%-10.2f\n", item.getProduct().getName(), item.getPrice()));
        });

        var message = String.format("""
                        Dear %s,
                        
                        Thank you for your order! We have successfully received your payment.
                        
                        %s
                        
                        Your order will be delivered to:
                        %s
                        
                        Expected delivery time: 5 days.
                        
                        Best regards,
                        Your Smartech Group Customer Services
                        """,
                order.getShippingAddress().getFullName(),
                itemList,
                order.getShippingAddress().getAddress()
        );

        return message;
    }
}
