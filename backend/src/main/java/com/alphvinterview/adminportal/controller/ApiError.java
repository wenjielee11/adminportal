package com.alphvinterview.adminportal.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A class representing an API error response.
 * 
 * This class is used to create a standard format for sending error details in the response body 
 * when an exception occurs in the API.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Ensures that null values are not included in the serialized JSON
public class ApiError {
    
    // HTTP status code of the error response
    private HttpStatus status;
    
    // A user-friendly message about the error
    private String message;
    
    // A list of detailed error messages or reasons for the error
    private List<String> errors;

    /**
     * Constructs a new ApiError with the specified status, message, and list of errors.
     *
     * @param status the HTTP status code of the error
     * @param message a user-friendly message about the error
     * @param errors a list of detailed error messages or reasons for the error
     */
    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Standard getters and setters for the properties

    /**
     * Gets the HTTP status code of the error response.
     *
     * @return the HTTP status code
     */
    public HttpStatus getStatus(){
        return this.status;
    }

    /**
     * Sets the HTTP status code of the error response.
     *
     * @param status the HTTP status code to set
     */
    public void setStatus(HttpStatus status){
        this.status = status;
    }

    /**
     * Gets the user-friendly error message.
     *
     * @return the error message
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * Sets the user-friendly error message.
     *
     * @param message the error message to set
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Gets the list of detailed error messages or reasons for the error.
     *
     * @return the list of error messages
     */
    public List<String> getErrors(){
        return errors;
    }

    /**
     * Sets the list of detailed error messages or reasons for the error.
     *
     * @param errors the list of error messages to set
     */
    public void setErrors(List <String> errors){
        this.errors = errors;
    }
}
