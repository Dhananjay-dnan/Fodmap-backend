package com.foodmap.foodmap_backend.mappers.impl;

import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.OrderItemDto;
import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.domain.entities.OrderItem;
import com.foodmap.foodmap_backend.mappers.OrderItemMapper;
import com.foodmap.foodmap_backend.repositories.FoodListRepository;

@Component
public class OrderItemMapperimpl implements OrderItemMapper {

    private final FoodListRepository foodListRepository;

    public OrderItemMapperimpl(FoodListRepository foodListRepository){
        this.foodListRepository =foodListRepository;
    }

    @Override
    public OrderItem fromDto(OrderItemDto orderItemDto) {
        FoodList foodList = foodListRepository.findById(orderItemDto.foodListId()).orElseThrow(() -> new RuntimeException("Food item not found"));
        OrderItem orderItem = new OrderItem();
        orderItem.setFoodList(foodList);
        orderItem.setQuantity(orderItemDto.quantity());
       return orderItem;
    }

    @Override
    public OrderItemDto toDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getFoodList().getId(), orderItem.getQuantity());
    }
    
}
