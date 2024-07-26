package com.realtura.listingsservice.client.subscriptions;

import com.realtura.listingsservice.dto.response.GenericResponse;
import com.realtura.listingsservice.model.Subscription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "realtura-subscription-service", url = "http://localhost:8051/api/v1/subscriptions")
public interface SubscriptionClient {
    @GetMapping("/{userId}")
    ResponseEntity<GenericResponse<Subscription>> getSubscriptionByUserId(@PathVariable Long userId, Long id);

    @PutMapping("/{userId}")
    ResponseEntity<GenericResponse<?>> useCredit(@PathVariable Long userId);
}
