package com.smartechgroup.e_commerce.util;

import com.smartechgroup.e_commerce.model.Product;
import com.smartechgroup.e_commerce.response.ProductResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductResponse toResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(String.valueOf(product.getId()));
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setOriginalPrice(product.getOriginalPrice());
        response.setImage(product.getImage());
        response.setImages(product.getImages());
        response.setCategory(product.getCategory() != null ? product.getCategory().getName() : null);
        response.setBrand(product.getBrand());
        response.setRating(product.getRating());
        response.setReviewCount(product.getReviewCount());
        response.setInStock(product.getInStock());
        response.setStockQuantity(product.getStockQuantity());
        response.setSpecifications(product.getSpecifications());
        response.setTags(product.getTags());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        response.setCreatedAt(product.getCreatedAt() != null ? product.getCreatedAt().format(formatter) : null);
        response.setUpdatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt().format(formatter) : null);

        return response;
    }
    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
