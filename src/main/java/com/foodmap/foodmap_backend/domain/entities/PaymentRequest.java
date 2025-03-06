package com.foodmap.foodmap_backend.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
        private Long amount;
        private String currency;
        private String paymentMethodId;
        private Long orderId;
}
