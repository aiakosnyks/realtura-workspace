package com.realtura.realturamain.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String nameSurname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private String country;
}
