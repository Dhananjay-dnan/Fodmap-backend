package com.foodmap.foodmap_backend.domain.entities;


import lombok.*;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String vehicleDetails;

    @OneToMany(mappedBy = "deliveryPerson")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Order> orders;
}
