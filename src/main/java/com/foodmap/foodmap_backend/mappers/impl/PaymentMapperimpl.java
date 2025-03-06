package com.foodmap.foodmap_backend.mappers.impl;

import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.PaymentDto;
import com.foodmap.foodmap_backend.domain.entities.Payment;
import com.foodmap.foodmap_backend.mappers.PaymentMapper;

@Component
public class PaymentMapperimpl implements PaymentMapper{

    @Override
    public Payment fromDto(PaymentDto paymentDto) {
        return new Payment(paymentDto.paymentIntentId(), null, paymentDto.amount(), paymentDto.status(), paymentDto.paymentTime());
    }

    @Override
    public PaymentDto toDto(Payment payment) {
        return new PaymentDto(payment.getPaymentIntentId(), null, payment.getAmount(), payment.getStatus(), payment.getPaymentTime());
    }
    
}
