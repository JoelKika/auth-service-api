package com.example.auth_service_api.controller.impl;

import com.example.auth_service_api.commons.dtos.TokenResponse;
import com.example.auth_service_api.commons.dtos.UserRequest;
import com.example.auth_service_api.controller.AuthApi;
import com.example.auth_service_api.service.AuthService;
import com.example.auth_service_api.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }
}
