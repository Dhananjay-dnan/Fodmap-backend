package com.foodmap.foodmap_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodmap.foodmap_backend.domain.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    
}
