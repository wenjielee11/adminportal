package com.alphvinterview.adminportal.service;
import com.alphvinterview.adminportal.model.User;

import java.util.List;

public interface UserServiceInterface {

    public User addUser(User user);
    public List<User> getAllActiveUsers();
    public User updateUser(User user);
    public void deleteUser(User user);
    public void hardDeleteById(User user);

}