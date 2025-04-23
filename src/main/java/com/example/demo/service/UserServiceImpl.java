package com.example.demo.service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.types.DuplicateEmailException;
import com.example.demo.exception.types.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) throws Exception {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new DuplicateEmailException(request.email());
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.password());
        user.setActive(true);

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with Id [%d] doesn't exist", id)));
        return userMapper.toResponse(user);
    }
}