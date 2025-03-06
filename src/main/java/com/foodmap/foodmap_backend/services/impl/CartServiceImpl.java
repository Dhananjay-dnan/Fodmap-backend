package com.foodmap.foodmap_backend.services.impl;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.Cart;
import com.foodmap.foodmap_backend.domain.entities.CartItem;
import com.foodmap.foodmap_backend.domain.entities.FoodList;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.repositories.CartItemRepository;
import com.foodmap.foodmap_backend.repositories.CartRepository;
import com.foodmap.foodmap_backend.repositories.FoodListRepository;
import com.foodmap.foodmap_backend.repositories.UserRepository;
import com.foodmap.foodmap_backend.services.CartService;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService{

    
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodListRepository foodListRepository;
    private final UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    
    public CartServiceImpl(CartRepository cartRepository, FoodListRepository foodListRepository, CartItemRepository cartItemRepository, UserRepository userRepository)
    {
        this.cartRepository = cartRepository ;
        this.cartItemRepository = cartItemRepository ;
        this.foodListRepository = foodListRepository ;
        this.userRepository = userRepository ;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
        .orElseGet(() -> {
            Cart newCart = new Cart();
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Cannot create cart for a user that doesn't exist"));
            user.setId(userId);
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });
    }

    @Override
    @Transactional
    public Cart addItemToCart(Long userId, Long foodListId) {
        Cart cart = getCartByUserId(userId);
        FoodList menuItem = foodListRepository.findById(foodListId)
            .orElseThrow(() -> new RuntimeException("Menu item not found"));
        CartItem cartItem = cart.getItems().stream()
            .filter(item -> item.getFoodList().getId().equals(foodListId))
            .findFirst()
            .orElseGet(() -> {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setFoodList(menuItem);
                newItem.setQuantity(0);
                cart.getItems().add(newItem);
                return newItem;
            });
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
        return cart;
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long foodListId) {
        Cart cart = getCartByUserId(userId);
        FoodList menuItem = foodListRepository.findById(foodListId)
            .orElseThrow(() -> new RuntimeException("Menu item not found"));
        CartItem cartItem = cart.getItems().stream()
            .filter(item -> item.getFoodList().getId().equals(foodListId))
            .findFirst()
            .orElseGet(() -> {
                CartItem newItem = new CartItem();
                newItem.setCart(cart);
                newItem.setFoodList(menuItem);
                newItem.setQuantity(0);
                cart.getItems().add(newItem);
                return newItem;
            });
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        cartItemRepository.save(cartItem);
        return cart;
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cartItemRepository.deleteByCart(cart);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public Map<String, String> getCart(String userId) {
        String key = "cart:" + userId;
     Map<Object, Object> rawMap = redisTemplate.opsForHash().entries(key);
    
    // // Convert Map<Object, Object> to Map<String, String>
    return rawMap.entrySet().stream()
            .collect(Collectors.toMap(
                e -> String.valueOf(e.getKey()), 
                e -> String.valueOf(e.getValue())
            ));
    
    }

    @Override
    public Map<String, String> addToCart(String userId, String foodId) {
        String key = "cart:" + userId;
        redisTemplate.opsForHash().increment(key, foodId, 1L);
        redisTemplate.expire(key, 1, TimeUnit.HOURS);
        return getCart(userId);
    }

    @Override
    public Map<String, String> removeFromCart(String userId, String foodId) {
        String key = "cart:" + userId;
        Long newQuantity = redisTemplate.opsForHash().increment(key, foodId, -1L);
        if (newQuantity <= 0) {
            redisTemplate.opsForHash().delete(key, foodId); // Remove if quantity reaches 0 or below
        }
        return getCart(userId);
    }
    
}
