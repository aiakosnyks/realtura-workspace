package com.realtura.realturamain.converter;

import com.realtura.realturamain.model.Subscription;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class SubscriptionConverter {
    public static Subscription convert(Long userId) {
        Subscription subscription = new Subscription();
        subscription.setUserId(userId);
        return subscription;
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
