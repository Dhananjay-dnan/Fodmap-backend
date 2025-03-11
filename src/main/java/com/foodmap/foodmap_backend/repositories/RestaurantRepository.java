package com.foodmap.foodmap_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{   
    @SuppressWarnings("null")
    Optional<Restaurant> findById(Long restaurantId);

    List<Restaurant> findByUserId(Long userId);
}
