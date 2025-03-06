package com.foodmap.foodmap_backend.mappers.impl;

import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.RestaurantDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.mappers.RestaurantMapper;

@Component
public class RestaurantMapperimpl implements RestaurantMapper{

    @Override
    public Restaurant fromDto(RestaurantDto restaurantDto) {
        return new Restaurant(restaurantDto.id(), restaurantDto.name(), restaurantDto.address(), restaurantDto.phone(), null,null);
    }

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhone());
    }
    
}
