package com.smartechgroup.e_commerce.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductRequest {

    private String name;

    private String description;

    private Double price;

    private Double originalPrice;

    private String image; // Primary image

    private List<String> images;

    private Long categoryId; // Instead of Category entity

    private String brand;

    private Double rating;

    private Integer reviewCount;

    private Boolean inStock;

    private Integer stockQuantity;

    private Map<String, String> specifications;

    private List<String> tags;
}