package com.alphvinterview.adminportal.model;

import java.time.Instant;
import jakarta.persistence.*;

/**
 * Represents a User entity in the database. Not performing any normalization
 * here as only shape and colors are determined by (id, name, lastname)
 */
@Entity
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = { "firstName", "lastName", "deleted" }) })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "shape")
    private String shape;

    @Column(name = "color")
    private String color;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "deleted")
    private Instant deleted; // Timestamp to indicate soft delete status

    // Entity lifecycle callbacks to set timestamps

    @PrePersist
    private void onCreate() {
        createdAt = updatedAt = Instant.now();
        deleted = null; // Not deleted at creation
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now();
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    // No setter for createdAt as it should not be changed after entity creation

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    // No setter for updatedAt as it is set automatically at entity update

    public Instant getDeleted() {
        return deleted;
    }

    /**
     * Marks the entity as deleted by setting the deleted timestamp, if not already
     * set.
     */
    public void setDeleted(Instant time) {
        if (deleted == null) {
            this.deleted = Instant.now();
        }
    }
}
