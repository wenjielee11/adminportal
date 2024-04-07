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

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                Collections.singletonList(e.getLocalizedMessage()/**new String()**/));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred accessing data. Ensure your paramters are correct",
                Collections.singletonList(ex.getLocalizedMessage()/**new String()**/));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred accessing data. Ensure your paramters are correct",
                Collections.singletonList(ex.getLocalizedMessage()/**new String()**/));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Duplicate Entry attempted. Make sure your names are unique.",
                Collections.singletonList(ex.getLocalizedMessage()/**new String()**/));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


    // Handle generic Exception (catch all)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred",
                Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}