package com.realtura.realturamain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Subscription {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
//                @OneToOne(cascade; = CascadeType.ALL)
//        @JoinColumn(name = "user_id", referencedColumnName = "id")
//        private User user;
        @Column(name = "user_id")
        private Long userId;
        @Column(name = "subscription_duration")
        private Integer subscriptionDuration = 0;
        @Column(name = "subscribed_until")
        private LocalDateTime subscribedUntil = LocalDateTime.now();
        private Integer credits = 0;

        public Subscription(Long userId) {
                this.userId = userId;
        }
}

