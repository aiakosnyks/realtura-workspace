package com.realtura.userservice.controller;

import com.realtura.userservice.dto.request.UserLoginRequest;
import com.realtura.userservice.dto.request.UserSaveRequest;
import com.realtura.userservice.dto.response.CreateResponse;
import com.realtura.userservice.dto.response.GenericResponse;
import com.realtura.userservice.model.User;
import com.realtura.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponse<CreateResponse>> save(@RequestBody UserSaveRequest request) {
        return new ResponseEntity<>(userService.save(request), HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public GenericResponse<Optional<User>> getById(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return GenericResponse.success(user);
    }

    @GetMapping("/email/{email}")
    public GenericResponse<Optional<User>> getByEmail(@PathVariable String email) {
        Optional<User> user = userService.getByEmail(email);
        return GenericResponse.success(user);
    }

    @PostMapping("/login")
    public GenericResponse<?> login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }
}
