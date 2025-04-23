package com.example.demo.service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request) throws Exception;
    UserResponse getUserById(Long id) throws Exception;
    List<UserResponse> getAllUsers();
}