package com.foodmap.foodmap_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.dto.RestaurantDto;
import com.foodmap.foodmap_backend.domain.entities.Restaurant;
import com.foodmap.foodmap_backend.mappers.RestaurantMapper;
import com.foodmap.foodmap_backend.services.RestaurantService;

@RestController
@RequestMapping(path = "/api/restaurants")
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public  RestaurantController (RestaurantService restaurantService, RestaurantMapper restaurantMapper){
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @PostMapping(path = "/create")
    public RestaurantDto createRestaurant(@RequestBody RestaurantDto restaurantDto){
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurantMapper.fromDto(restaurantDto));
        return restaurantMapper.toDto(createdRestaurant);
    }

    @PutMapping
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, long restaurant_id)
    {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant_id, restaurantMapper.fromDto(restaurantDto));
        return restaurantMapper.toDto(updatedRestaurant);
    }

    @GetMapping
    public List<RestaurantDto> listRestaurants(){
        return restaurantService.listRestaurants().stream().map(restaurantMapper::toDto).toList();
    }
    
}
