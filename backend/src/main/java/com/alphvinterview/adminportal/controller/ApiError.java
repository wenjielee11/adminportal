package com.alphvinterview.adminportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters and Setters
    public HttpStatus getStatus(){
        return this.status;
    }
    public void setStatus(HttpStatus status){
        this.status = status;
    }
    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public List<String> getErrors(){
        return errors;
    }
    public void setErrors(List <String> errors){
        this.errors = errors;
    }
    
}