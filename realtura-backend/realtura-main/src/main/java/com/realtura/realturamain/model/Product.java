package com.realtura.realturamain.model;

import lombok.Data;

@Data
public final class Product {
    private String title = "Standard";
    private Integer subscriptionDuration = 30;
    private double price = 100.0;
    private Integer credits = 10;
    private Integer allowedQuantity = 10;
}
