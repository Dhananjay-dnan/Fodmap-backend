package com.foodmap.foodmap_backend.domain.dto;

import java.util.Set;

import com.foodmap.foodmap_backend.domain.entities.Role;

public record UserDto(Long id,String username, String email, Role role, Set<Long> restaurantId) {
    
}
