package com.crm.crm_backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.LoginRequest;
import com.crm.crm_backend.DTO.LoginResponse;
import com.crm.crm_backend.DTO.LogoutRequest;
import com.crm.crm_backend.auth.AuthenticationService;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.util.ApiResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid
        @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Login Successful",
                        service.login(request)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @Valid @RequestBody LogoutRequest request) {

        service.logout(request.getRefreshToken());

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Logout Successful",
                        null));

    }
    
}
