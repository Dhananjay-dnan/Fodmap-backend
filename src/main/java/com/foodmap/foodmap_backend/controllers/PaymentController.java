package com.foodmap.foodmap_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.entities.PaymentRequest;
import com.foodmap.foodmap_backend.domain.entities.PaymentResponse;
import com.foodmap.foodmap_backend.payment.service.PaymentIntentService;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping(path = "/api/payment")
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
public class PaymentController {

    @Autowired
    private PaymentIntentService paymentIntentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws Exception {
        PaymentIntent paymentIntent = paymentIntentService.createPaymentIntent(paymentRequest.getAmount(), paymentRequest.getCurrency(), paymentRequest.getPaymentMethodId(), paymentRequest.getOrderId());
        PaymentResponse paymentResponse = new PaymentResponse(paymentIntent.getClientSecret());
        return ResponseEntity.ok(paymentResponse);
    }
    
}
