package com.crm.crm_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.crm.crm_backend.DTO.CreateRoleRequest;
import com.crm.crm_backend.DTO.RoleResponse;
import com.crm.crm_backend.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	Role toEntity(CreateRoleRequest request);

	RoleResponse toResponse(Role role);

	void updateRole(CreateRoleRequest request, @MappingTarget Role role);

}