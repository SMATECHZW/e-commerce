package com.smartechgroup.e_commerce.service;

import com.smartechgroup.e_commerce.model.Category;
import com.smartechgroup.e_commerce.model.Product;
import com.smartechgroup.e_commerce.request.ProductRequest;
import com.smartechgroup.e_commerce.response.ProductResponse;
import com.smartechgroup.e_commerce.response.Response;

import java.util.List;
import java.util.Optional;

public interface ProductService extends GenericService<Product> {
    List<Product> getProductsByCategory(Category category);
    Response createProduct(ProductRequest request);
    Response updateProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<ProductResponse> getAllProducts();
    Optional<ProductResponse> getProductResponseById(Long id);
}
