package com.alphvinterview.adminportal.controller;

import java.util.Collections;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.EntityNotFoundException;

/**
 * Centralized exception handling across all @RequestMapping methods
 * through @ExceptionHandler methods.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions thrown when method arguments are not valid.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error",
                Collections.singletonList(e.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Handles exceptions related to data access failures.
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Data access error. Check parameters.",
                Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Handles cases where an entity is not found in the database.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Entity not found.",
                Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Handles exceptions when a duplicate key insertion is attempted.
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Duplicate entry detected.",
                Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * General exception handler that catches all other exceptions not specifically
     * handled.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "General error occurred.",
                Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
