package com.foodmap.foodmap_backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.entities.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant, MultipartFile file) throws IOException;
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);
    List<Restaurant> listRestaurants();
    List<Restaurant> getOwnedRestaurant(Long userId);
    Optional<Restaurant> getRestaurant(Long restaurantId);

}
