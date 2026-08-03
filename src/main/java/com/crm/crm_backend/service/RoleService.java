package com.crm.crm_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.CreateRoleRequest;
import com.crm.crm_backend.DTO.RoleResponse;

@Service
public interface RoleService {

	RoleResponse create(CreateRoleRequest request);

	RoleResponse update(Long id, CreateRoleRequest request);

	RoleResponse getById(Long id);

	List<RoleResponse> getAll();

	void delete(Long id);

}