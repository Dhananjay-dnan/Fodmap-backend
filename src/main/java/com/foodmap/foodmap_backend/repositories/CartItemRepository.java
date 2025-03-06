package com.foodmap.foodmap_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.Cart;
import com.foodmap.foodmap_backend.domain.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    void deleteByCart(Cart cart);
}
