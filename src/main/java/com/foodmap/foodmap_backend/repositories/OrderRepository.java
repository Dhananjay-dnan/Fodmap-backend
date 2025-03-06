package com.foodmap.foodmap_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{

    List<Order> findByRestaurantId(Long restaurantId);
    @SuppressWarnings("null")
    Optional<Order> findById(Long orderId);
    List<Order> findByUserId(Long userId);
    Page<Order> findByUserId(Long userId, Pageable pageable);
    
}
