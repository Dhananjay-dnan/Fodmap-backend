package com.foodmap.foodmap_backend.services;

import java.util.Map;

import com.foodmap.foodmap_backend.domain.entities.Cart;

public interface CartService {

    
    

    Cart getCartByUserId(Long userId);
    Cart addItemToCart(Long userId, Long foodListId);
    void clearCart(Long userId);
    Cart removeItemFromCart(Long userId, Long foodListId);
    Map<String, String> getCart(String userId);
    Map<String, String> addToCart(String userId, String foodId);
    Map<String, String> removeFromCart(String userId, String foodId);
    Map<String, String> clearFromCart(String userId);
} 
