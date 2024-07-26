package com.realtura.realturamain.client.user.service;

import com.realtura.realturamain.client.user.UserClient;
import com.realtura.realturamain.dto.response.GenericResponse;
import com.realtura.realturamain.dto.request.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

//    private final SubscriptionService subscriptionService;
    private final UserClient userClient;


    public Optional<User> getUserById(Long userId) {
        GenericResponse<Optional<User>> response = userClient.getById(userId);
        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getError());
        }
        return response.getData();
    }

//    public void saveSubscription(Long userId, String subscriptionId) {
//        Optional<User> foundUser = getUserById(userId);
//        if (foundUser == null) {
//            log.error("User not found with id: {}", userId);
//        }
//        else {
//            Subscription subscription = SubscriptionConverter.convert(foundUser.get()); // 10 ilan, 30 gün geçerlilik
//            subscriptionService.save(subscription);
//        }
//    }
}

