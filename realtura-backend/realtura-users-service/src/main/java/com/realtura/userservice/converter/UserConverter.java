package com.realtura.userservice.converter;

import com.realtura.userservice.dto.request.UserSaveRequest;
import com.realtura.userservice.dto.response.UserResponse;
import com.realtura.userservice.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {

    public static User toUser(UserSaveRequest request) {
        return User.builder()
                .nameSurname(request.getNameSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getUsername())
                .isActive(true)
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .nameSurname(user.getNameSurname())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isActive(user.getIsActive())
                .country(user.getCountry())
                .build();
    }

    public static Set<UserResponse> toResponse(List<User> users) {
        return users
                .stream()
                .map(UserConverter::toResponse)
                .collect(Collectors.toSet());
    }
}
