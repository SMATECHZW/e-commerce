package com.smartechgroup.e_commerce.request;

import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private List<PaymentItem> items;
    private ShippingAddressRequest shippingAddress;
    private String paymentMethod;
    private double subtotal;
    private double tax;
    private double shipping;
    private double total;
    private String ecocashNumber;
    private Long userId;
}