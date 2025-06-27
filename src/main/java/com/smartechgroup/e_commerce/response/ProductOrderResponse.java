package com.smartechgroup.e_commerce.response;

import lombok.Data;

@Data
public class ProductOrderResponse {
    private Long id;
    private String name;
    private double price;
    private String image;
}
