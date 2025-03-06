package com.foodmap.foodmap_backend.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.foodmap.foodmap_backend.domain.entities.PaymentStatus;

public record PaymentDto(String paymentIntentId,
        Long orderId,
        BigDecimal amount,
        PaymentStatus status,
        LocalDateTime paymentTime) {

}
