package com.foodmap.foodmap_backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.repositories.FoodListRepository;
import com.foodmap.foodmap_backend.repositories.RestaurantRepository;
import com.foodmap.foodmap_backend.services.FoodListService;

@Service
public class FoodListServiceImpl implements FoodListService{

    private final FoodListRepository foodListRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodListServiceImpl(FoodListRepository foodListRepository, RestaurantRepository restaurantRepository){
        this.foodListRepository = foodListRepository;
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public List<FoodList> findByRestaurantId(Long restaurantId) {
        return foodListRepository.findByRestaurantId(restaurantId);
        
    }
    @Override
    public Optional<FoodList> findById(Long foodListId) {
        return foodListRepository.findById(foodListId);
    }   
    @Override
    public List<FoodList> findByCategory(String category) {
        return foodListRepository.findByCategory(category);
    }
    @Override
    public List<FoodList> listFoodList() {
        return foodListRepository.findAll();
    }
    @Override
    public FoodList createFoodList(Long restaurantId, FoodList foodList) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with ID: " + restaurantId));
    //     if (foodList.getImage() == null || foodList.getImage().isEmpty()) {
    //         throw new IllegalArgumentException("Image  should not be null");
    // }

        // FoodList newFoodList = new FoodList(null, foodList.getName(), foodList.getImage(), foodList.getPrice(), foodList.getDescription(), foodList.getCategory(), restaurant);
        foodList.setRestaurant(restaurant);
        return foodListRepository.save(foodList);
    }
    
}
