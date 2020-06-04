package com.assignment.spring.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * Logger for rest exception handler.
     */
    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handler for runtime exception.
     *
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        logger.debug("error processing request", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

