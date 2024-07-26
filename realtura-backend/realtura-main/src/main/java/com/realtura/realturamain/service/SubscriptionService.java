package com.realtura.realturamain.service;

import com.realtura.realturamain.client.user.service.UserService;
import com.realtura.realturamain.dto.request.SubscriptionSaveRequest;
import com.realtura.realturamain.dto.response.CreateResponse;
import com.realtura.realturamain.dto.response.GenericResponse;
import com.realtura.realturamain.exception.RealturaException;
import com.realtura.realturamain.model.Product;
import com.realtura.realturamain.model.Subscription;
import com.realtura.realturamain.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
@Service
public class SubscriptionService {

    private final UserService userService;
    private final SubscriptionRepository subscriptionRepository;
    private final Product product;

    private boolean processPayment(Long userId, double amount, Integer productQuantity) {

        if(amount == product.getPrice() * productQuantity) {
            log.info("Processing payment for user: " + userId + ", amount: " + amount);
            return true;
        }
        log.error("Processing payment for user: " + userId + " FAILED");
        return false;
    }


    @Transactional()
    public GenericResponse<?> purchasePackage(SubscriptionSaveRequest request) {

        Long userId = request.getUserId();
        double amount = request.getAmount();
        Integer productQuantity = request.getProductQuantity();

        if (userService.getUserById(userId).isEmpty()) {
            log.error("User " + userId + " not found");
            return GenericResponse.failed("User not found");
        }
        if (processPayment(userId, amount, productQuantity)) {
            Optional<Subscription> optionalSubscription = subscriptionRepository.findByUserId(userId);
            Subscription created = null;
            if (optionalSubscription.isPresent()) {
                Subscription subscription = optionalSubscription.get();
                Integer newCredits = subscription.getCredits() + productQuantity * product.getCredits();
                int daysToAdd = productQuantity * product.getSubscriptionDuration() + subscription.getSubscriptionDuration();
                subscription.setSubscriptionDuration(daysToAdd);
                subscription.setCredits(newCredits);
                subscription.setSubscribedUntil(subscription.getSubscribedUntil().plusDays(daysToAdd));
                created = subscriptionRepository.save(subscription);
                log.info("Subscription extended for user: " + userId + ", new subscription duration: " + subscription.getSubscriptionDuration());
            } else {
                Subscription subscription = new Subscription(userId);
                subscription.setCredits(product.getCredits());
                subscription.setSubscriptionDuration(product.getSubscriptionDuration());
                subscription.setSubscribedUntil(LocalDateTime.now().plusDays(product.getSubscriptionDuration()));
                created = subscriptionRepository.save(subscription);
                log.info("Subscription created for user: " + userId);
            }
            return GenericResponse.success(new CreateResponse(created.getId()));
        } else {
            throw new RuntimeException("Payment failed");
        }
    }

    public GenericResponse<Subscription> getSubscriptionsByUserId(Long userId) {
        Optional<Subscription> subscription = subscriptionRepository.findByUserId(userId);
        if (subscription.isEmpty()) {
            log.error("User " + userId + " not found");
            throw new RealturaException("User not found");
        }
        return GenericResponse.success(subscription.get());
    }

    public GenericResponse<?> update(Long subscriptionId) {
        Optional<Subscription> subscription = subscriptionRepository.findById(subscriptionId);
        if (subscription.isEmpty()) {
            log.error("Subscription {} not found", subscriptionId);
            throw new RealturaException("Subscription not found");
        }
        Subscription subscriptionToBeUpdated = subscription.get();
        subscriptionToBeUpdated.setCredits(subscriptionToBeUpdated.getCredits() - 1);
        Subscription updated = subscriptionRepository.save(subscriptionToBeUpdated);
        return GenericResponse.success(updated);
    }
}



