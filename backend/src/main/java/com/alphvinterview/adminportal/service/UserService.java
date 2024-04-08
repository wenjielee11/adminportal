package com.alphvinterview.adminportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alphvinterview.adminportal.model.*;
import com.alphvinterview.adminportal.repository.*;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.List;
import java.time.Instant;

/**
 * Service layer for handling user operations. Updates user portal via websocket
 * Implements UserServiceInterface to define its operations.
 */
@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Constructor for UserService with dependency injection.
     * 
     * @param messagingTemplate Template for messaging via websockets.
     */
    @Autowired
    public UserService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Adds a new user. Validates uniqueness based on firstName, lastName, and not
     * being deleted.
     * 
     * @param user User to add.
     * @return The saved User entity.
     */
    @Override
    public User addUser(User user) {
        Optional<User> userOptional = userRepository.findByFirstNameAndLastNameAndDeletedEquals(
                user.getFirstName(), user.getLastName(), null);

        if (userOptional.isPresent()) {
            throw new DuplicateKeyException("This entry violates a unique constraint.");
        }
        user.setId(null); // Ensure ID is null for JPA to consider it a new entity
        User result = userRepository.save(user);
        messagingTemplate.convertAndSend("/topic/userUpdate", getAllActiveUsers());
        return result;
    }

    /**
     * Updates an existing user's information based on ID or firstName and lastName
     * if ID is not provided.
     * 
     * @param user User with updated fields.
     * @return The updated User entity.
     */
    @Override
    @Transactional
    public User updateUser(User user) {
        Optional<User> optUser;

        if (user.getId() != null) {
            optUser = userRepository.findByIdAndNotDeleted(user.getId(), null);
        } else {
            optUser = userRepository.findByFirstNameAndLastNameAndDeletedEquals(user.getFirstName(), user.getLastName(),
                    null);
        }
        if (!optUser.isPresent()) {
            throw new EntityNotFoundException("User not found");
        }

        User u = optUser.get();
        // Update non-null fields from the provided user
        if (user.getColor() != null)
            u.setColor(user.getColor());
        if (user.getFirstName() != null)
            u.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            u.setLastName(user.getLastName());
        if (user.getShape() != null)
            u.setShape(user.getShape());

        User result = userRepository.save(u);
        messagingTemplate.convertAndSend("/topic/userUpdate", getAllActiveUsers());
        return result;
    }

    /**
     * Retrieves all users that haven't been marked as deleted.
     * 
     * @return List of all active users.
     */
    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findByDeleted(null);
    }

    /**
     * Performs a hard delete of a user by their ID.
     * 
     * @param user User to delete.
     */
    @Override
    public void hardDeleteById(User user) {
        userRepository.hardDeleteById(user.getId());
    }

    /**
     * Marks a user as deleted by setting the deleted timestamp. Throws if user is
     * already deleted or not found.
     * 
     * @param user User to mark as deleted.
     */
    @Override
    @Transactional
    public void deleteUser(User user) {
        if (user.getId() == null)
            throw new EntityNotFoundException("Cannot delete without id");

        Optional<User> userOptional = userRepository.findByFirstNameAndLastNameAndIdAndDeletedEquals(
                user.getFirstName(), user.getLastName(), user.getId(), null);

        if (userOptional.isPresent()) {
            User u = userOptional.get();
            if (u.getDeleted() != null) {
                throw new EntityNotFoundException("User already deleted");
            } else {
                userRepository.markAsDeleted(u.getId(), Instant.now());
            }
        } else {
            throw new EntityNotFoundException("User not found");
        }
        messagingTemplate.convertAndSend("/topic/userUpdate", getAllActiveUsers());
    }
}
