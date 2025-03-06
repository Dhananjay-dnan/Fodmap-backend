package com.foodmap.foodmap_backend.services;

import java.util.List;
import java.util.Optional;

import com.foodmap.foodmap_backend.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);
    List<Restaurant> listRestaurants();
    Optional<Restaurant> getRestaurant(Long restaurantId);
}
