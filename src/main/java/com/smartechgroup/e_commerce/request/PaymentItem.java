package com.smartechgroup.e_commerce.request;

import lombok.Data;

@Data
public class PaymentItem {
    private String productId;
    private int quantity;
    private double price;
}