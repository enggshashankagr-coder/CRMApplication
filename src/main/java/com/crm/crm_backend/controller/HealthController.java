package com.crm.crm_backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.util.ApiResponseUtil;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

	@GetMapping
	public ResponseEntity<ApiResponse<Map<String, String>>> health() {

		Map<String, String> response = Map.of("application", "CRM Backend", "status", "UP");

		return ResponseEntity.ok(ApiResponseUtil.success("Application is running successfully", response));

	}

}