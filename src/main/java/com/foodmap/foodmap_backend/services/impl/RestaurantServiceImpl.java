package com.foodmap.foodmap_backend.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.repositories.RestaurantRepository;
import com.foodmap.foodmap_backend.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final String uploadDir;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
        this.uploadDir = System.getProperty("user.dir") + "/uploads";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
    }
    
    @Override
    public Restaurant createRestaurant(Restaurant restaurant, MultipartFile file) throws IOException {
        if(restaurant.getId()!=null)
            throw new IllegalArgumentException("Restaurant id should not be null");
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());
        Restaurant newRestaurant = new Restaurant(null, restaurant.getName(), restaurant.getAddress(), fileName, restaurant.getPhone(), null, null, restaurant.getUser());
        //newRestaurant.setImage(fileName);
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

    @Override
    public List<Restaurant> getOwnedRestaurant(Long userId) {
        return restaurantRepository.findByUserId(userId);
    }
    
    
}
