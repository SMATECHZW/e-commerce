package com.smartechgroup.e_commerce.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private int quantity;
    private ProductOrderResponse product;
}