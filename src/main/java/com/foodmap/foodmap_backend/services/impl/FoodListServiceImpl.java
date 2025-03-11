package com.foodmap.foodmap_backend.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.repositories.FoodListRepository;
import com.foodmap.foodmap_backend.repositories.RestaurantRepository;
import com.foodmap.foodmap_backend.services.FoodListService;

@Service
public class FoodListServiceImpl implements FoodListService{

    private final FoodListRepository foodListRepository;
    private final RestaurantRepository restaurantRepository;
    private final String uploadDir;
    public FoodListServiceImpl(FoodListRepository foodListRepository, RestaurantRepository restaurantRepository){
        this.foodListRepository = foodListRepository;
        this.restaurantRepository = restaurantRepository;
        this.uploadDir = System.getProperty("user.dir") + "/uploads";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
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
    public FoodList createFoodList(Long restaurantId, FoodList foodList, MultipartFile file) throws IOException {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with ID: " + restaurantId));
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());
    //     if (foodList.getImage() == null || foodList.getImage().isEmpty()) {
    //         throw new IllegalArgumentException("Image  should not be null");
    // }

        // FoodList newFoodList = new FoodList(null, foodList.getName(), foodList.getImage(), foodList.getPrice(), foodList.getDescription(), foodList.getCategory(), restaurant);
        foodList.setImage(fileName);
        foodList.setRestaurant(restaurant);
        return foodListRepository.save(foodList);
    }
    
}
