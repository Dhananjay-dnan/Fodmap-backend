package com.foodmap.foodmap_backend.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import com.foodmap.foodmap_backend.domain.entities.OrderStatus;

public record OrderDto(Long id,Long userId, Long restaurantId, OrderStatus orderStatus,String deliveryAddress, BigDecimal price, List<OrderItemDto> orderItems)
{}