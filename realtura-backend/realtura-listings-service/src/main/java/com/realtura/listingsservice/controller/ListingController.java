package com.realtura.listingsservice.controller;

import com.realtura.listingsservice.dto.request.ListingDeleteRequest;
import com.realtura.listingsservice.dto.request.ListingSaveRequest;
import com.realtura.listingsservice.dto.request.ListingSearchRequest;
import com.realtura.listingsservice.dto.response.CreateResponse;
import com.realtura.listingsservice.dto.response.GenericResponse;
import com.realtura.listingsservice.dto.response.ListingResponse;
import com.realtura.listingsservice.service.ListingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<GenericResponse<?>> save(@RequestBody ListingSaveRequest request) {
        log.info("New subscriptions saved: {}", request);
        return new ResponseEntity<>(listingService.save(request), HttpStatus.OK);
    }

    @PostMapping("/getAllByFilter")
    public ResponseEntity<GenericResponse<List<ListingResponse>>> getAllByFilter(@RequestBody ListingSearchRequest request) {
        return new ResponseEntity<>(GenericResponse.success(listingService.getAllByFilter(request)), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse<?>> update(@PathVariable Long id, @RequestBody ListingSaveRequest request) {
        return new ResponseEntity<>(listingService.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<?>> delete(@RequestBody ListingDeleteRequest request) {
        return new ResponseEntity<>(listingService.delete(request), HttpStatus.OK);
    }
}
