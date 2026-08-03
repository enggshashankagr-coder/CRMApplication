package com.crm.crm_backend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.LoginRequest;
import com.crm.crm_backend.DTO.LoginResponse;
import com.crm.crm_backend.DTO.RefreshTokenRequest;
import com.crm.crm_backend.DTO.RefreshTokenResponse;
import com.crm.crm_backend.entity.RefreshToken;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.UserRepository;
import com.crm.crm_backend.security.CustomUserDetailsService;
import com.crm.crm_backend.service.JwtService;
import com.crm.crm_backend.service.RefreshTokenService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl
        implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;
    
    private final RefreshTokenService refreshTokenService;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final JwtProperties jwtProperties;
    
    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        UserDetails userDetails =
        		userDetailsService.loadUserByUsername(request.getUsername());

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        String accessToken = jwtService.generateToken(userDetails);

        RefreshToken refreshToken =
                refreshTokenService.create(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .expiresIn(jwtProperties.getExpiration())
                .username(user.getUsername())
                .build();
    }

    @Override
    @Transactional
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        // Verify refresh token
        RefreshToken refreshToken =
                refreshTokenService.verify(request.getRefreshToken());

        User user = refreshToken.getUser();

        // Load Spring Security UserDetails
        UserDetails userDetails =
        		userDetailsService.loadUserByUsername(user.getUsername());

        // Generate new access token
        String accessToken = jwtService.generateToken(userDetails);

        // Refresh Token Rotation
        refreshTokenService.revoke(request.getRefreshToken());

        RefreshToken newRefreshToken =
                refreshTokenService.create(user);

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken.getToken())
                .expiresIn(jwtProperties.getExpiration())
                .build();
    }
    
    @Override
    @Transactional
    public void logout(String refreshToken) {

        refreshTokenService.revoke(refreshToken);

    }

	/*
	 * @Override public LoginResponse login(LoginRequest request) {
	 * 
	 * authenticationManager.authenticate(
	 * 
	 * new UsernamePasswordAuthenticationToken( request.getUsername(),
	 * request.getPassword()));
	 * 
	 * UserDetails user = userDetailsService
	 * .loadUserByUsername(request.getUsername());
	 * 
	 * String token = jwtService.generateToken(user);
	 * 
	 * return LoginResponse.builder() .accessToken(token)
	 * .username(user.getUsername())
	 * .expiresIn(jwtProperties.getExpiration()).tokenType("Bearer") .build(); }
	 */
}
