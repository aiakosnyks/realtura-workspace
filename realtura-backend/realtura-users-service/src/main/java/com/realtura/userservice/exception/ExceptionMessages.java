package com.realtura.userservice.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String USER_NOT_FOUND = "kullanıcı bulunamadı.";
    public static final String USER_NOT_ACTIVE = "kullanıcı aktif değil.";
    public static final String EMAIL_ALREADY_EXIST = "bu email ile kayıtlı kullanıcı var";
    public static final String PASSWORD_INCORRECT = "şifre hatalı.";
}
