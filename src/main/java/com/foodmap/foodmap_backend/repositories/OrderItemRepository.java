package com.foodmap.foodmap_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodmap.foodmap_backend.domain.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
