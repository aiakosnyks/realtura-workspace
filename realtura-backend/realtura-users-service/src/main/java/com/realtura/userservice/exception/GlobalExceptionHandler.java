package com.realtura.userservice.exception;


import com.realtura.userservice.dto.response.ExceptionResponse;
import com.realtura.userservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RealturaException.class)
    public GenericResponse<ExceptionResponse> handleException(RealturaException exception) {
        log.error(exception.getLocalizedMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
