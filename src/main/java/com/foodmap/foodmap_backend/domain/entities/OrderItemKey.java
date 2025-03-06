package com.foodmap.foodmap_backend.domain.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderItemKey implements Serializable{

    private Long orderId;

    private Long foodListId;

    // Constructors
    public OrderItemKey() {}

    public OrderItemKey(Long orderId, Long foodListId) {
        this.orderId = orderId;
        this.foodListId = foodListId;
    }
    
}
