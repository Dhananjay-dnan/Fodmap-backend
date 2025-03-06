package com.foodmap.foodmap_backend.mappers;

import com.foodmap.foodmap_backend.domain.dto.RestaurantDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;

public interface RestaurantMapper {

    Restaurant fromDto(RestaurantDto restaurantDto);
    RestaurantDto toDto(Restaurant restaurant);
    
}
