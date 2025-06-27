package com.smartechgroup.e_commerce.service;

import com.smartechgroup.e_commerce.model.Order;
import com.smartechgroup.e_commerce.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getOrdersByUserId(Long userId);
    List<Order> getOrdersByCustomerNotified(Boolean customerNotified);
}
