package com.foodmap.foodmap_backend.mappers;

import com.foodmap.foodmap_backend.domain.dto.OrderItemDto;
import com.foodmap.foodmap_backend.domain.entities.OrderItem;

public interface OrderItemMapper {
     OrderItem fromDto(OrderItemDto orderItemDto);
    OrderItemDto toDto(OrderItem orderItem);
}
