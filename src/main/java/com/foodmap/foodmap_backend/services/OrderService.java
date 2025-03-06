package com.foodmap.foodmap_backend.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foodmap.foodmap_backend.domain.entities.Order;
import com.foodmap.foodmap_backend.domain.entities.OrderStatus;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrderStatus(String orderId, OrderStatus orderStatus);
    List<Order> listOrdersByRestaurant(Long restaurantId);
    OrderStatus getOrderStatus(Long orderId);
    Order getOrder(Long userId);
    List<Order> listOrders(Long userId);
    Page<Order> listOrders(Long userId,Pageable pageable);
}
