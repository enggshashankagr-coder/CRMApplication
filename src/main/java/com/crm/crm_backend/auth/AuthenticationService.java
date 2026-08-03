package com.crm.crm_backend.auth;

import com.crm.crm_backend.DTO.LoginRequest;
import com.crm.crm_backend.DTO.LoginResponse;
import com.crm.crm_backend.DTO.RefreshTokenRequest;
import com.crm.crm_backend.DTO.RefreshTokenResponse;

public interface AuthenticationService {

	  LoginResponse login(LoginRequest request);
	  RefreshTokenResponse refreshToken(RefreshTokenRequest request);
	  void logout(String refreshToken);
}
