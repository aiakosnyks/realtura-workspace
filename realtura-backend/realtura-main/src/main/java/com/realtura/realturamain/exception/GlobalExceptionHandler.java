package com.realtura.realturamain.exception;


import com.realtura.realturamain.dto.response.ExceptionResponse;
import com.realtura.realturamain.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RealturaException.class)
    public GenericResponse<ExceptionResponse> handleException(RealturaException exception) {
        log.error(exception.getMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
