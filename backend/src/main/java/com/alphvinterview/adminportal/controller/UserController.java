package com.alphvinterview.adminportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alphvinterview.adminportal.model.User;
import com.alphvinterview.adminportal.service.UserService;
import com.alphvinterview.adminportal.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for user administration operations.
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://ec2-54-251-0-255.ap-southeast-1.compute.amazonaws.com") // Allows cross-origin requests from the specified domain.
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a new user based on the provided UserDto.
     * @param entity UserDto containing user data
     * @return ResponseEntity containing the created UserDto
     */
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto entity) {
        User user = userService.addUser(toEntity(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(user));
    }

    /**
     * Edits an existing user's details.
     * @param entity UserDto with updated user data
     * @return ResponseEntity with the edited UserDto
     */
    @PutMapping("/edit")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto entity) {
        User user = userService.updateUser(toEntity(entity));
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(toDto(user));
    }

    /**
     * Soft Deletes a user based on the provided UserDto. Marks them as deleted, but records persist.
     * @param entity UserDto containing user data for deletion
     * @return ResponseEntity confirming deletion
     */
    @PostMapping("/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto entity) {
        userService.deleteUser(toEntity(entity));
        return ResponseEntity.ok(entity);
    }

    /**
     * Retrieves a list of active users.
     * @return ResponseEntity with a list of UserDtos for active users
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getActiveUsers() {
        List<User> users = userService.getAllActiveUsers();
        List<UserDto> userDtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    // @PostMapping("/hard-delete")
    // public ResponseEntity<UserDto> hardDelete(@RequestBody UserDto entity) {
    //     userService.hardDeleteById(toEntity(entity));
    //     return ResponseEntity.status(HttpStatus.OK).body(entity);
    // }

    // Conversion methods for User and UserDto entities

    /**
     * Converts a UserDto to a User entity.
     * @param userDto DTO representation of a user
     * @return User entity
     */
    private User toEntity(UserDto userDto) {
        User user = new User();
        // Set properties from DTO to User entity
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setShape(userDto.getShape());
        user.setColor(userDto.getColor());
        return user;
    }

    /**
     * Converts a User entity to a UserDto.
     * @param user User entity
     * @return UserDto representation of the user
     */
    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        // Set properties from User entity to DTO
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setShape(user.getShape());
        userDto.setColor(user.getColor());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        return userDto;
    }
}
