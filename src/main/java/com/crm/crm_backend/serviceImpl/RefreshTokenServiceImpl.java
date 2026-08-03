package com.crm.crm_backend.serviceImpl;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.crm_backend.auth.RefreshTokenProperties;
import com.crm.crm_backend.entity.RefreshToken;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.repository.RefreshTokenRepository;
import com.crm.crm_backend.service.RefreshTokenService;
import com.crm.crm_backend.util.TokenGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl
        implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    private final RefreshTokenProperties properties;

    @Override
    public RefreshToken create(User user) {

        RefreshToken refreshToken = RefreshToken.builder()
                .token(TokenGenerator.generateRefreshToken())
                .expiryDate(LocalDateTime.now()
                        .plusSeconds(properties.getExpiration() / 1000))
                .user(user)
                .build();

        return repository.save(refreshToken);
    }

    @Override
    public RefreshToken verify(String token) {

        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found"));

        if (Boolean.TRUE.equals(refreshToken.getRevoked())) {
            throw new RuntimeException("Refresh token revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    @Override
    public void revoke(String token) {

        RefreshToken refreshToken = verify(token);

        refreshToken.setRevoked(true);

        repository.save(refreshToken);
    }

    @Override
    public void revokeAll(User user) {

        repository.deleteByUser(user);
    }
}
