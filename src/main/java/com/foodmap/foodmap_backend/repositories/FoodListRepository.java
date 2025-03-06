package com.foodmap.foodmap_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.FoodList;


@Repository
public interface FoodListRepository extends JpaRepository<FoodList, Long>{
    List<FoodList> findByRestaurantId(Long restaurantId);
    List<FoodList> findByCategory(String category);

}

    
