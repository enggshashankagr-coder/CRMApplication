package com.crm.crm_backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.RefreshTokenRequest;
import com.crm.crm_backend.DTO.RefreshTokenResponse;
import com.crm.crm_backend.auth.AuthenticationService;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.util.ApiResponseUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final AuthenticationService authenticationService;

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Token refreshed successfully",
                        authenticationService.refreshToken(request)));
    }
}
