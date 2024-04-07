package com.alphvinterview.adminportal.dto;



import java.time.Instant;

import javax.validation.constraints.*;

public class UserDto {
    @Null(message = "firstName cannot be null")
    @Size(min = 2, message = "first name must be at least 2 characters long")
    private String firstName;
    
    private Long id;
    @NotNull(message = "lastName cannot be null")
    @Size(min = 2, message = "last name must be at least 2 characters long")
    private String lastName;
    
    private String shape;
    private String color;
    private Instant createdAt;
    private Instant updatedAt;

   
    public Instant getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt){
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt){
        this.updatedAt = updatedAt;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
