package com.foodmap.foodmap_backend.payment.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class PaymentIntentService {

    public PaymentIntent createPaymentIntent(Long amount, String currency, String paymentMethodId, Long orderId) throws Exception {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("order_id", orderId.toString());
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount(amount) // amount in smallest currency unit (e.g., cents)
                .setCurrency(currency)
                .addPaymentMethodType("card")
                .setPaymentMethod(paymentMethodId)
                .setDescription("Food Delivery Order")
                .putAllMetadata(metadata)
                .build();

        return PaymentIntent.create(createParams);
    }
}