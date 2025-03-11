package com.foodmap.foodmap_backend.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.dto.RestaurantDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.mappers.RestaurantMapper;
import com.foodmap.foodmap_backend.services.RestaurantService;

@RestController
@RequestMapping(path = "/api/restaurants")
@CrossOrigin(origins = { "http://localhost:5174", "http://localhost:5173" })
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @PostMapping(path = "/create/{user_id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public RestaurantDto createRestaurant(@PathVariable("user_id") Long userId,@RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            @RequestParam("image") MultipartFile file) {
        RestaurantDto restaurantDto = new RestaurantDto(null, name, null, address, phone, userId);
        System.out.println("AAAAAAAAAAAAaaaaaaaaaaaa"+userId);
        Restaurant createdRestaurant;
        try {
            createdRestaurant = restaurantService.createRestaurant(restaurantMapper.fromDto(restaurantDto), file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image: " + e.getMessage());
        }
        return restaurantMapper.toDto(createdRestaurant);
    }

    @PutMapping
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, long restaurant_id) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant_id,
                restaurantMapper.fromDto(restaurantDto));
        return restaurantMapper.toDto(updatedRestaurant);
    }

    @GetMapping
    public List<RestaurantDto> listRestaurants() {
        return restaurantService.listRestaurants().stream().map(restaurantMapper::toDto).toList();
    }

    @GetMapping(path = "/{user_id}/owned")
    public List<RestaurantDto> listRestaurantsOfOwner(@PathVariable("user_id") Long userId) {
        return restaurantService.getOwnedRestaurant(userId).stream().map(restaurantMapper::toDto).toList();
    }

}
