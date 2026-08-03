package com.crm.crm_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.DTO.CreateRoleRequest;
import com.crm.crm_backend.DTO.RoleResponse;
import com.crm.crm_backend.common.exception.DuplicateResourceException;
import com.crm.crm_backend.common.exception.ResourceNotFoundException;
import com.crm.crm_backend.entity.Role;
import com.crm.crm_backend.mapper.RoleMapper;
import com.crm.crm_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	public RoleMapper mapper;

	@Override
	public RoleResponse create(CreateRoleRequest request) {

		// log.info("Creating Role : {}",request.getRoleName());

		if (repository.existsByRoleNameIgnoreCase(request.getRoleName())) {

			throw new DuplicateResourceException("Role already exists");

		}

		Role role = mapper.toEntity(request);

		repository.save(role);

		return mapper.toResponse(role);

	}

	@Override
	// @Transactional(readOnly = true)
	public List<RoleResponse> getAll() {

		return repository.findAll().stream().filter(r -> !r.getDeleted()).map(mapper::toResponse).toList();

	}

	@Override
	// @Transactional(readOnly = true)
	public RoleResponse getById(Long id) {

		Role role = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		return mapper.toResponse(role);

	}

	@Override
	public void delete(Long id) {

		Role role = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		role.setDeleted(true);

		role.setActive(false);

	}

	@Override
	public RoleResponse update(Long id, CreateRoleRequest request) {

		Role role = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		mapper.updateRole(request, role);

		return mapper.toResponse(role);

	}

}
