package com.alphvinterview.adminportal.controller;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alphvinterview.adminportal.model.User;
import com.alphvinterview.adminportal.service.UserService;
import com.alphvinterview.adminportal.dto.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController{

    @Autowired
    UserService userService;

    
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto entity) {

       User user = userService.addUser(toEntity(entity));

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(user));
    }
    @PutMapping("/edit")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto entity) {
        User user = userService.updateUser(toEntity(entity));
        
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(toDto(user));
    }
    @PostMapping("/delete")
    public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto entity) {
        userService.deleteUser(toEntity(entity));
        
        return ResponseEntity.status(HttpStatus.OK).body(entity);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getActiveUsers() {
        List<User> users = userService.getAllActiveUsers();
        List<UserDto> userDtos = users.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }


    // @PostMapping("/hard-delete")
    // public ResponseEntity<UserDto> hardDelete(@RequestBody UserDto entity) {
    //     userService.hardDeleteById(toEntity(entity));
    //     return ResponseEntity.status(HttpStatus.OK).body(entity);
    // }
    

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setShape(userDto.getShape());
        user.setColor(userDto.getColor());

        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
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