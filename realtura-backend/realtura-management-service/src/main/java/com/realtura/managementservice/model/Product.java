package com.realtura.managementservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Duration;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {
    @Id
    private Long productId = 1L;
    private String title = "Standard";
    private Period subscriptionDuration = Period.ofDays(30);
    private double price = 100.0;
    private Integer credits = 10;
    private Integer allowedQuantity = 10;
}
