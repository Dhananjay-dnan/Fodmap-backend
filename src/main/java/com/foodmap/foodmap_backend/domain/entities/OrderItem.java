package com.foodmap.foodmap_backend.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItems")
public class OrderItem {

    @EmbeddedId
    private OrderItemKey id = new OrderItemKey();


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId") // Maps 'orderId' of embedded id with 'order_id' foreign key
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("foodListId")
    @JoinColumn(name = "foodlist_id")
    private FoodList foodList;

    @Column(nullable = false)
    private Integer quantity;

    
}
