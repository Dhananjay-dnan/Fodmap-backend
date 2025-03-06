package com.foodmap.foodmap_backend.domain.entities;

public class PaymentResponse {
    private String clientSecret;

    public PaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
