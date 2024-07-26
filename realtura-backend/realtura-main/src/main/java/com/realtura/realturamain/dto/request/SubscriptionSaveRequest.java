package com.realtura.realturamain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionSaveRequest {
    private Long userId;
    private double amount;
    private Integer productQuantity;
}
