package com.crm.crm_backend.service;

import java.util.List;

import com.crm.crm_backend.DTO.CreateUserRequest;
import com.crm.crm_backend.DTO.UserResponse;

public interface UserService {
	UserResponse create(CreateUserRequest request);

    UserResponse getById(Long id);

    List<UserResponse> getAll();

    void delete(Long id);
}
