package com.foodmap.foodmap_backend.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.domain.entities.Order;
import com.foodmap.foodmap_backend.domain.entities.OrderItem;
import com.foodmap.foodmap_backend.domain.entities.OrderStatus;
import com.foodmap.foodmap_backend.repositories.FoodListRepository;
import com.foodmap.foodmap_backend.repositories.OrderRepository;
import com.foodmap.foodmap_backend.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{


    @Autowired
    private FoodListRepository foodListRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setOrderStatus(order.getOrderStatus());
        order.setOrderTime(LocalDateTime.now());
       BigDecimal totalAmount = BigDecimal.ZERO;

       for( OrderItem newOrderItem : order.getOrderItems()){
        
        FoodList foodItem = foodListRepository.findById(newOrderItem.getFoodList().getId())
                    .orElseThrow(() -> new RuntimeException("Food item not found"));
            newOrderItem.setFoodList(foodItem);
            BigDecimal itemTotal = foodItem.getPrice().multiply(BigDecimal.valueOf(newOrderItem.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
            // Set the order reference in OrderItem
            newOrderItem.setOrder(order);

       }
       order.setTotalAmount(totalAmount);
       return orderRepository.save(order);

    }

    @Override
    public Order updateOrderStatus(String orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(Long.parseLong(orderId)).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
        
    }

    @Override
    public OrderStatus getOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return order.getOrderStatus();

    }

    @Override
    public Order getOrder(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
    return orders.isEmpty() ? null : orders.get(orders.size() - 1);
        
        
    }

    @Override
    public List<Order> listOrders(Long userId) {
       return orderRepository.findByUserId(userId);
    }

    @Override
    public Page<Order> listOrders(Long userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }
    
}
