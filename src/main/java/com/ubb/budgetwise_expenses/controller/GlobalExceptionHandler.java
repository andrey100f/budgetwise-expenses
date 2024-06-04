package com.ubb.budgetwise_expenses.controller;

import com.ubb.budgetwise_expenses.model.exception.InvalidResourceException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidResourceException.class})
    public Map<String, String> handleInvalidResourceException(InvalidResourceException exception) {
        return Map.of("status", HttpStatus.BAD_REQUEST.toString(),
            "message", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({FeignException.class})
    public Map<String, String> handleFeignException(FeignException exception) {
        return Map.of("status", HttpStatus.NOT_FOUND.toString(),
            "message", exception.getMessage());
    }

}
