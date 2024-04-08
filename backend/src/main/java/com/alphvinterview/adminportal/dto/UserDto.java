package com.alphvinterview.adminportal.dto;

import java.time.Instant;
import javax.validation.constraints.*;

/**
 * Data Transfer Object for User details.
 */
public class UserDto {

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must be at least 2 characters long")
    private String firstName;

    // Unique identifier for the User
    private Long id;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must be at least 2 characters long")
    private String lastName;

    // Shape associated with the User
    private String shape;

    // Color associated with the User
    private String color;

    // Timestamp when the User was created
    private Instant createdAt;

    // Timestamp when the User details were last updated
    private Instant updatedAt;

    // Standard getters and setters

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
