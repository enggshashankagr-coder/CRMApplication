package com.crm.crm_backend.DTO;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String fullName;

    private String mobileNo;

    private Boolean active;

    private Set<String> roles;
}