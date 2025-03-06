package com.foodmap.foodmap_backend.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.services.FoodListService;

@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping(path = "/api")
public class FoodListController {
    private final FoodListService foodListService;
    public FoodListController(FoodListService foodListService){
        this.foodListService=foodListService;
    }

    @GetMapping(path = "/{restaurantId}")
    List<FoodList> listFoodList(@PathVariable Long restaurantId)
    {
       return foodListService.findByRestaurantId(restaurantId);
    }

    @GetMapping(path = "/fooditem/{foodListId}") 
    Optional<FoodList> getFoodList(@PathVariable Long foodListId)
    {
        return foodListService.findById(foodListId);
    }

    @GetMapping(path = "/category/{category}/fooditem")
    List<FoodList> listFoodListByCategory(@PathVariable String category){
        return foodListService.findByCategory(category);
    }

    @GetMapping(path = "/foodlist")
    List<FoodList> listFoodList(){
        return foodListService.listFoodList();
    }

    @PostMapping(path="/create/{restauarantId}/foodlist", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    FoodList createFoodList(@PathVariable Long restaurantId, @RequestParam("name") String name,
@RequestParam("price") BigDecimal price,
@RequestParam("description") String description,
@RequestParam("category") String category,
@RequestParam("image") MultipartFile file){
        FoodList foodList = new FoodList(null, name, null, price, description, category, null, null);
        return foodListService.createFoodList(restaurantId, foodList);
       }
}
