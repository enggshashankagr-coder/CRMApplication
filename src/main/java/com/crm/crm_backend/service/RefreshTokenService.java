package com.crm.crm_backend.service;

import com.crm.crm_backend.entity.RefreshToken;
import com.crm.crm_backend.entity.User;

public interface RefreshTokenService {

    RefreshToken create(User user);

    RefreshToken verify(String token);

    void revoke(String token);

    void revokeAll(User user);

}
