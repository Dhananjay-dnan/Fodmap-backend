package com.foodmap.foodmap_backend.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.entities.Cart;
import com.foodmap.foodmap_backend.services.CartService;

@RequestMapping(path = "/api/cart")
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }
    @GetMapping(path = "/{userId}")
    Cart getCart(@PathVariable Long userId) {
    
        return cartService.getCartByUserId(userId);
    }

    @PostMapping(path = "/{userId}/add/{foodListId}")
    Cart addToCart(@PathVariable Long userId, @PathVariable Long foodListId)
    {
        return cartService.addItemToCart(userId, foodListId);
    }

    @PostMapping(path = "/{userId}/remove/{foodListId}")
    Cart removeFromCart(@PathVariable Long userId, @PathVariable Long foodListId)
    {
        return cartService.removeItemFromCart(userId, foodListId);
    }

    @DeleteMapping(path="/{userId}/clear-cart")
    void clearCart(@PathVariable Long userId)
    {
        cartService.clearCart(userId);
    }

    @GetMapping("/red/{userId}")
    public ResponseEntity<Map<String, String>> getCart(@PathVariable String userId) {
        Map<String, String> cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }
    
    @PostMapping("/{userId}/red/add/{foodListId}")
    public ResponseEntity<Map<String, String>> addToCart(
            @PathVariable String userId,
            @PathVariable String foodListId
            ) {
                Map<String, String> cart = cartService.addToCart(userId, foodListId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/{userId}/red/remove/{foodListId}")
    public ResponseEntity<Map<String, String>> removeFromCart(
            @PathVariable String userId,
            @PathVariable String foodListId
            ) {
                Map<String, String> cart = cartService.removeFromCart(userId, foodListId);
        return ResponseEntity.ok(cart);
    }
}
