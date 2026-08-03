package com.crm.crm_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.crm_backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleNameIgnoreCase(String roleName);

	boolean existsByRoleNameIgnoreCase(String roleName);
}