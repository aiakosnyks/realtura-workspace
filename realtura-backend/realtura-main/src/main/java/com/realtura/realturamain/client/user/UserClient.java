package com.realtura.realturamain.client.user;

import com.realtura.realturamain.dto.response.GenericResponse;
import com.realtura.realturamain.dto.request.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@FeignClient(value = "realtura-user-service", url = "http://localhost:8080/api/v1/users")
public interface UserClient {

    @PostMapping
    void save(@RequestBody User request);

    @GetMapping
    List<User> getAll();

    @GetMapping("/{id}")
    GenericResponse<Optional<User>> getById(@PathVariable Long id);

    @GetMapping("/email/{email}")
    GenericResponse<Optional<User>> getByEmail(@PathVariable String email) ;

}

