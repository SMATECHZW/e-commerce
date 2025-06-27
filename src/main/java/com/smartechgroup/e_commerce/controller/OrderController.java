package com.smartechgroup.e_commerce.controller;

import com.smartechgroup.e_commerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(id), HttpStatus.OK);
    }
}
