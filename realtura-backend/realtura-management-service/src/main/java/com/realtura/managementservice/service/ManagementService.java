package com.realtura.managementservice.service;

import com.realtura.managementservice.model.Product;
import com.realtura.managementservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class ManagementService {

    private final SubscriptionRepository subscriptionRepository;

    public boolean processPayment(Long userId, double amount, Product product) {

        // Ödemenin başarılı olduğunu simüle edin
        // Gerçek bir sistemde, burada gerçek bir ödeme sağlayıcısı ile entegrasyon yapılır
        if(amount == product.getPrice()) {
            System.out.println("Processing payment for user: " + userId + ", amount: " + amount);
            return true;
        }
        return false;}

    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public void purchasePackage(Long userId, Product product) {
        if (processPayment(userId, product.getPrice(), product)) {
            // Ödeme başarılı oldu, kullanıcıya paket ekle

            UserPackage userPackage = new UserPackage(userId, 10, 30); // 10 ilan, 30 gün geçerlilik
            SubscriptionRepository.save(userPackage);
        } else {
            // Ödeme başarısız
            throw new RuntimeException("Payment failed");
        }
    }

}

