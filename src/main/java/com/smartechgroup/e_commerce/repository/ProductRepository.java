package com.smartechgroup.e_commerce.repository;

import com.smartechgroup.e_commerce.model.Category;
import com.smartechgroup.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from Product p where p.category =: category")
    List<Product> productsByCategory(@Param("category") Category category);
    Product findByName(String name);
    @Query(value = "select p from Product p where p.id =: id")
    Product findProductById(@Param("id") Long id);
}
