package com.foodmap.foodmap_backend.domain.dto;

public record RestaurantDto(Long id, String name, String image, String address, String phone, Long userId) {
    
}
