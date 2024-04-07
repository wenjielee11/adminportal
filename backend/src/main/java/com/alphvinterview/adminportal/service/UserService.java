package com.alphvinterview.adminportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alphvinterview.adminportal.model.*;
import com.alphvinterview.adminportal.repository.*;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.time.Instant;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Optional<User> userOptional = userRepository.findByFirstNameAndLastNameAndDeletedEquals(user.getFirstName(),
                user.getLastName(), null);

        if (userOptional.isPresent()) {
            throw new DuplicateKeyException("This entry violates a unique constraint.");
        }
        user.setId(null); // Creates new entry
        User result = userRepository.save(user);
        return result;
    }

    @Override
    @Transactional
    public User updateUser(User user) {

        Optional<User> optUser = null;

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
        if (user.getColor() != null)
            u.setColor(user.getColor());
        if (user.getFirstName() != null)
            u.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            u.setLastName(user.getLastName());
        if (user.getShape() != null)
            u.setShape(user.getShape());
        User result = userRepository.save(u);
        return result;
    }

    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findByDeleted(null);
    }

    @Override
    public void hardDeleteById(User user) {
        userRepository.hardDeleteById(user.getId());
    }

    /**
     * Soft deletes user by setting delete bit to 1
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
    }
}