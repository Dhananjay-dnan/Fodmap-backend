package com.foodmap.foodmap_backend.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.repositories.RestaurantRepository;
import com.foodmap.foodmap_backend.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }
    
    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        if(restaurant.getId()!=null)
            throw new IllegalArgumentException("Restaurant id should not be null");
        Restaurant newRestaurant = new Restaurant(null, restaurant.getName(), restaurant.getAddress(), restaurant.getPhone(), null, null);
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        if(restaurant.getId()==null)
            throw new IllegalArgumentException("Task should have an id");
        if(!Objects.equals(restaurantId, restaurant.getId()))
            throw new IllegalArgumentException("Id's should be matched");
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Restaurant Not Found"));
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setName(restaurant.getName());

        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public List<Restaurant> listRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
    
    
}
