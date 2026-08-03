package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.CreateRoleRequest;
import com.crm.crm_backend.DTO.RoleResponse;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.service.RoleService;
import com.crm.crm_backend.util.ApiResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor

@Tag(name = "Role Management", description = "APIs for managing Roles")
public class RoleController {

	@Autowired
	public RoleService service;

	@PostMapping
	public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody CreateRoleRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponseUtil.success("Role Created Successfully", service.create(request)));

	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {

		return ResponseEntity.ok(

				ApiResponseUtil.success("Success", service.getAll()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> get(@PathVariable Long id) {

		return ResponseEntity.ok(

				ApiResponseUtil.success("Success", service.getById(id)));

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<RoleResponse>> update(@PathVariable Long id,

			@Valid

			@RequestBody CreateRoleRequest request) {

		return ResponseEntity.ok(

				ApiResponseUtil.success("Updated Successfully",

						service.update(id, request)));

	}

	@Operation(summary = "Delete Role")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> delete(@PathVariable Long id) {

		service.delete(id);

		return ResponseEntity.ok(ApiResponseUtil.success("Deleted Successfully"));

	}
}
