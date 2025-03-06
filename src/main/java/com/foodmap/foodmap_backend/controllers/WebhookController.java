package com.foodmap.foodmap_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.foodmap.foodmap_backend.domain.entities.OrderStatus;
import com.foodmap.foodmap_backend.services.OrderService;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payment")
public class WebhookController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @Autowired
    private OrderService orderService;

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.OK)
    public void handleStripeWebhook(
            HttpServletRequest request, @RequestBody String payload) {
                String sigHeader = request.getHeader("Stripe-Signature");
        // We'll implement this method in further steps
               Event event;

        try {
        // Verify webhook signature and extract the event
        event = Webhook.constructEvent(
                payload, sigHeader, endpointSecret
        );
    } catch (Exception e) {
        // Invalid payload or signature
        System.err.println("⚠️  Webhook error: " + e.getMessage());
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid signature."
        );
    }

    switch (event.getType()) {
        case "payment_intent.succeeded":
            handlePaymentIntentSucceeded(event);
            break;
        // ... handle other event types
        default:
            System.out.println("Unhandled event type: " + event.getType());
            break;
    }

        
    }

    private void handlePaymentIntentSucceeded(Event event) {
        PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer()
                .getObject()
                .orElse(null);

        if (paymentIntent == null) {
            System.err.println("PaymentIntent is null.");
            return;
        }

        // Retrieve order_id from PaymentIntent metadata
        String orderId = paymentIntent.getMetadata().get("order_id");

        if (orderId != null) {
            // Update order status in your database
            orderService.updateOrderStatus(orderId, OrderStatus.PLACED);
            System.out.println("Order " + orderId + " status updated to 'placed'.");
        } else {
            System.err.println("order_id not found in PaymentIntent metadata.");
        }
    }
    
}
    