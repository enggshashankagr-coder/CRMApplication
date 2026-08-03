package com.crm.crm_backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.crm_backend.DTO.CreateUserRequest;
import com.crm.crm_backend.DTO.UserResponse;
import com.crm.crm_backend.common.exception.DuplicateResourceException;
import com.crm.crm_backend.common.exception.ResourceNotFoundException;
import com.crm.crm_backend.entity.Role;
import com.crm.crm_backend.entity.User;
import com.crm.crm_backend.mapper.UserMapper;
import com.crm.crm_backend.repository.RoleRepository;
import com.crm.crm_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	public UserRepository userRepository;

	@Autowired
    public RoleRepository roleRepository;
	@Autowired
    public UserMapper mapper;
	@Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(CreateUserRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = mapper.toEntity(request);

        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>(
                roleRepository.findAllById(request.getRoleIds()));

        user.setRoles(roles);

        User saved = userRepository.save(user);

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapper.toResponse(user);

    }

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> getAll() {

	    return userRepository.findAll()
	            .stream()
	            .filter(u -> !u.getDeleted())
	            .map(mapper::toResponse)
	            .toList();

	}

	@Override
	public void delete(Long id) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found"));

	    user.setDeleted(true);
	    user.setActive(false);

	}

}
