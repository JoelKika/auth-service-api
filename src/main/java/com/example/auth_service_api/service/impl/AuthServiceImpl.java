package com.example.auth_service_api.service.impl;

import com.example.auth_service_api.commons.dtos.TokenResponse;
import com.example.auth_service_api.commons.dtos.UserRequest;
import com.example.auth_service_api.commons.entities.UserModel;
import com.example.auth_service_api.repositories.UserRepository;
import com.example.auth_service_api.service.AuthService;
import com.example.auth_service_api.service.JwtService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(()->new RuntimeException("Error creating user"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .name(userRequest.getName())
                .role("USER")
                .build();
    }
}