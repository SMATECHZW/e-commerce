package com.smartechgroup.e_commerce.controller;

import com.smartechgroup.e_commerce.model.Product;
import com.smartechgroup.e_commerce.request.ProductRequest;
import com.smartechgroup.e_commerce.response.Response;
import com.smartechgroup.e_commerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductResponseById(id), HttpStatus.OK);
    }
    @PostMapping("/add")
    public Response save(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
}
