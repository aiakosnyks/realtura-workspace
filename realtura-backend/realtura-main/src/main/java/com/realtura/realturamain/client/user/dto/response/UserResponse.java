package com.realtura.realturamain.client.user.dto.response;

import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String nameSurname;
    private String username;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private String country;
    private byte[] profilePicture;
    private Period subscriptionDuration;
    private LocalDateTime subscribedUntil;

}
