package com.foodmap.foodmap_backend.mappers.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.UserDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.mappers.UserMapper;
import com.foodmap.foodmap_backend.repositories.RestaurantRepository;

@Component
public class UserMapperImpl implements UserMapper{

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public UserMapperImpl(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public User fromDto(UserDto userDto) {
        //Set<Restaurant> restaurants = userDto.restaurantId().stream().map(restaurantRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
            
        
        return new User(userDto.id(), userDto.username(), null, null, userDto.email(), null, userDto.role(), null, null, null);
    }

    @Override
    public UserDto toDto(User user) {
        //Set<Long> restaurantIds = new ArrayList<>(user.getRestaurantsOwned()).stream().map(restaurant->restaurant.getId()).collect(Collectors.toSet());
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole(), null);
    }
    
    
}
