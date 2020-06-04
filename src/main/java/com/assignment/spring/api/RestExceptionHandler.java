package com.assignment.spring.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global error handler for REST Api.
 */
@ControllerAdvice
public class RestExceptionHandler {

    /**
     * Handler for runtime exception.
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getCause() + ex.getMessage());
    }
}

