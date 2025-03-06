package com.foodmap.foodmap_backend.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foodList")
public class FoodList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long for auto-generated IDs

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image; // Assuming this is a URL or path to the image

    @Column(nullable = false)
    private BigDecimal price; // Use Double for currency values

    @Column(length = 500) // Set a reasonable length for descriptions
    private String description;

    
    private String category;

 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "foodList")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<OrderItem> orderItems;



}
