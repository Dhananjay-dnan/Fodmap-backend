package com.foodmap.foodmap_backend.mappers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.RestaurantDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.mappers.RestaurantMapper;
import com.foodmap.foodmap_backend.repositories.UserRepository;

@Component
public class RestaurantMapperimpl implements RestaurantMapper{

   
    private final UserRepository userRepository;

    @Autowired
    public RestaurantMapperimpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Restaurant fromDto(RestaurantDto restaurantDto) {
        User user=userRepository.findById(restaurantDto.userId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new Restaurant(restaurantDto.id(), restaurantDto.name(), restaurantDto.address(), null, restaurantDto.phone(),null, null, user);
    }

    @Override
    public RestaurantDto toDto(Restaurant restaurant) {
        return new RestaurantDto(restaurant.getId(), restaurant.getName(), restaurant.getImage(), restaurant.getAddress(), restaurant.getPhone(), restaurant.getUser().getId());
    }
    
}
