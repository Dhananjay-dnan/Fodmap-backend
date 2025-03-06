package com.foodmap.foodmap_backend.services;

import java.util.List;
import java.util.Optional;

import com.foodmap.foodmap_backend.domain.entities.FoodList;

public interface FoodListService {
    List<FoodList> findByRestaurantId(Long restaurantId);
    Optional<FoodList> findById(Long foodListId);
    List<FoodList> findByCategory(String category);
    List<FoodList> listFoodList();
    FoodList createFoodList(Long restaurantId ,FoodList foodList);
}
