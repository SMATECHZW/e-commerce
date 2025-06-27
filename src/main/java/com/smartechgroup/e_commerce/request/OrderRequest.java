package com.smartechgroup.e_commerce.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Order items are required")
    private List<OrderItemRequest> orderItems;

    private String orderNumber;
    private Double totalAmount;
    private Double taxAmount;
    private Double shippingAmount;
    private String shippingAddress;
    private String billingAddress;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;
    private String trackingNumber;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
}