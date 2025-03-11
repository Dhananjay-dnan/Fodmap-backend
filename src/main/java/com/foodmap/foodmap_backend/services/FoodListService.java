package com.foodmap.foodmap_backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.entities.FoodList;

public interface FoodListService {
    List<FoodList> findByRestaurantId(Long restaurantId);
    Optional<FoodList> findById(Long foodListId);
    List<FoodList> findByCategory(String category);
    List<FoodList> listFoodList();
    FoodList createFoodList(Long restaurantId ,FoodList foodLis, MultipartFile file) throws IOException;
}
