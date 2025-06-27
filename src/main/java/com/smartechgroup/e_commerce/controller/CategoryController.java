package com.smartechgroup.e_commerce.controller;

import com.smartechgroup.e_commerce.model.Category;
import com.smartechgroup.e_commerce.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllCategories() {

        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getOneCategory(@PathVariable Long id) {

        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }
}
