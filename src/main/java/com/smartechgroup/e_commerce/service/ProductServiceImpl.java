package com.smartechgroup.e_commerce.service;

import com.smartechgroup.e_commerce.model.Category;
import com.smartechgroup.e_commerce.model.Product;
import com.smartechgroup.e_commerce.repository.CategoryRepository;
import com.smartechgroup.e_commerce.repository.ProductRepository;
import com.smartechgroup.e_commerce.request.ProductRequest;
import com.smartechgroup.e_commerce.response.ProductResponse;
import com.smartechgroup.e_commerce.response.Response;
import com.smartechgroup.e_commerce.util.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.productsByCategory(category);
    }

    @Override
    @Transactional
    public Response createProduct(ProductRequest request) {

        var productExists = productRepository.findByName(request.getName());
        if (productExists != null) {
            return new Response(400,"Product already exists", productExists);
        }
        var product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .originalPrice(request.getOriginalPrice())
                .image(request.getImage())
                .images(request.getImages())
                .category(categoryRepository.findById(request.getCategoryId()).orElseThrow())
                .brand(request.getBrand())
                .rating(request.getRating())
                .reviewCount(request.getReviewCount())
                .inStock(request.getInStock())
                .stockQuantity(request.getStockQuantity())
                .specifications(request.getSpecifications())
                .tags(request.getTags())
                .build();
        productRepository.save(product);
        return new Response(200,"Product created successfully", product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Response updateProduct(Product product) {
        productRepository.save(product);
        return new Response(200,"Product updated successfully", product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products = productRepository.findAll();
        return ProductMapper.toResponseList(products);
    }

    @Override
    public Optional<ProductResponse> getProductResponseById(Long id) {
        var product = getProductById(id);
        return Optional.of(ProductMapper.toResponse(product.orElseThrow()));
    }
}
