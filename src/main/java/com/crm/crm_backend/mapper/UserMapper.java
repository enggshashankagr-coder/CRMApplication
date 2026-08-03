package com.crm.crm_backend.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.crm.crm_backend.DTO.CreateUserRequest;
import com.crm.crm_backend.DTO.UserResponse;
import com.crm.crm_backend.entity.User;

@Component
public class UserMapper {

	public User toEntity(CreateUserRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setMobileNo(request.getMobileNo());

        return user;
    }

    public UserResponse toResponse(User user) {

        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toSet());

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .mobileNo(user.getMobileNo())
                .active(user.getActive())
                .roles(roles)
                .build();
    }

}

