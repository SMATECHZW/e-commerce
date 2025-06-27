package com.smartechgroup.e_commerce.repository;

import com.smartechgroup.e_commerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUserId(Long userId);
    List<Order> findOrdersByCustomerNotified(Boolean customerNotified);
}
