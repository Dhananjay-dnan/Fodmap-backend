package com.foodmap.foodmap_backend.mappers;

import com.foodmap.foodmap_backend.domain.dto.PaymentDto;
import com.foodmap.foodmap_backend.domain.entities.Payment;

public interface PaymentMapper {
     Payment fromDto(PaymentDto paymentDto);
    PaymentDto toDto(Payment payment);
}
