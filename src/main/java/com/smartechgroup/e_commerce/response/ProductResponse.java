package com.smartechgroup.e_commerce.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Double originalPrice;
    private String image;
    private List<String> images;
    private String category; // Just the name or ID, depending on your preference
    private String brand;
    private Double rating;
    private Integer reviewCount;
    private Boolean inStock;
    private Integer stockQuantity;
    private Map<String, String> specifications;
    private List<String> tags;
    private String createdAt;
    private String updatedAt;
}