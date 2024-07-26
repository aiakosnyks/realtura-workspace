package com.realtura.managementservice.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionSaveRequest {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Period subscriptionDuration;
}
