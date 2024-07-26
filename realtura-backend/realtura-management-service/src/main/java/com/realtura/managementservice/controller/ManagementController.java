package com.realtura.managementservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ManagementController {

    @GetMapping("/validate/{userId}")
    public ResponseEntity<Boolean> validateListing(@PathVariable Long userId) {
        // Kullanıcı doğrulama işlemi
        boolean isValidUser = true; // Mock valid user
        return ResponseEntity.ok(isValidUser);
    }

    @GetMapping("/packages/{userId}")
    public ResponseEntity<List<Package>> getUserPackages(@PathVariable Long userId) {
        // Kullanıcının paket bilgilerini al
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("Standard", 10, LocalDate.now().plusDays(30)));
        return ResponseEntity.ok(packages);
    }
}