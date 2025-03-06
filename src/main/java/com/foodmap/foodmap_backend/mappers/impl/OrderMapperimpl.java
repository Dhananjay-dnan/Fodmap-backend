package com.foodmap.foodmap_backend.mappers.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.OrderDto;
import com.foodmap.foodmap_backend.domain.dto.OrderItemDto;
import com.foodmap.foodmap_backend.domain.entities.Order;
import com.foodmap.foodmap_backend.domain.entities.OrderItem;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.mappers.OrderItemMapper;
import com.foodmap.foodmap_backend.mappers.OrderMapper;
import com.foodmap.foodmap_backend.services.RestaurantService;
import com.foodmap.foodmap_backend.services.UserService;

@Component
public class OrderMapperimpl implements OrderMapper {

    
    private final OrderItemMapper orderItemMapper;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public OrderMapperimpl (OrderItemMapper orderItemMapper, RestaurantService restaurantService, UserService userService){
        this.orderItemMapper = orderItemMapper;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @Override
    public Order fromDto(OrderDto orderDto) {

        List<OrderItem> orderItem= orderDto.orderItems()
        .stream()
        .map(orderItemMapper::fromDto) // Maps each OrderItemDto to OrderItem
        .collect(Collectors.toList());
        Restaurant restaurant = restaurantService.getRestaurant(orderDto.restaurantId()).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        User user = userService.getUser(orderDto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new Order(orderDto.id(), user, restaurant, null, orderDto.orderStatus(), null, null, orderDto.price(), orderDto.deliveryAddress(), orderItem);
    }

    @Override
    public OrderDto toDto(Order order) {
         List<OrderItemDto> orderItemDto= order.getOrderItems()
        .stream()
        .map(orderItemMapper::toDto) // Maps each OrderItemDto to OrderItem
        .collect(Collectors.toList());
        return new OrderDto(order.getOrderId(), order.getUser().getId(), order.getRestaurant().getId(), order.getOrderStatus(), order.getDeliveryAddress(), order.getTotalAmount(), orderItemDto);
    }
    
}
