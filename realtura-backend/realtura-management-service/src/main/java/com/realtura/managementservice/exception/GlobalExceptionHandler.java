package com.realtura.managementservice.exception;

import com.patika.kitapyurdum.dto.response.ExceptionResponse;
import com.patika.kitapyurdum.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(KitapYurdumException.class)
    public GenericResponse<ExceptionResponse> handleException(KitapYurdumException exception) {
        log.error(exception.getLocalizedMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
