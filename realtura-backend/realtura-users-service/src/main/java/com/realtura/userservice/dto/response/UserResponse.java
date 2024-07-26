package com.realtura.userservice.dto.response;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String nameSurname;
    private String username;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private String country;
    private Period subscriptionDuration;
    private LocalDateTime subscribedUntil;
}
