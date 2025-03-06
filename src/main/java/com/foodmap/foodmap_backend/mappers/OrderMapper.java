package com.foodmap.foodmap_backend.mappers;


import com.foodmap.foodmap_backend.domain.dto.OrderDto;
import com.foodmap.foodmap_backend.domain.entities.Order;

public interface OrderMapper {
     Order fromDto(OrderDto orderDto);
    OrderDto toDto(Order order);
}
