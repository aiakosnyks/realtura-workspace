package com.realtura.listingsservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription {
        private Long id;
        private Long userId;
        private Integer subscriptionDuration = 0;
        private LocalDateTime subscribedUntil = LocalDateTime.now();
        private Integer credits = 0;
}

