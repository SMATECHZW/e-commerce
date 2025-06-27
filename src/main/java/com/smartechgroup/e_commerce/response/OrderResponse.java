package com.smartechgroup.e_commerce.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String paymentMethod;
    private String paymentStatus;
    private ShippingAddressResponse shippingAddress;
    private List<OrderItemResponse> items;
    private double subtotal;
    private double tax;
    private double shipping;
    private double total;
}