package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.State;
import com.crm.crm_backend.serviceImpl.StateServiceImpl;

@RestController
	@RequestMapping("/api/v1/states")
	
	public class StateController {
			
		@Autowired
	    private  StateServiceImpl stateService;

	    @PostMapping
	    public ResponseEntity<ApiResponse<State>> save(@RequestBody State state) {

	        return ResponseEntity.status(HttpStatus.CREATED).body(
	                ApiResponse.<State>builder()
	                        .success(true)
	                        .message("State saved successfully.")
	                        .data(stateService.save(state))
	                        .build());
	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse<List<State>>> getAll() {

	        return ResponseEntity.ok(
	                ApiResponse.<List<State>>builder()
	                        .success(true)
	                        .message("State list fetched successfully.")
	                        .data(stateService.getAll())
	                        .build());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<State>> getById(@PathVariable Long id) {

	        return ResponseEntity.ok(
	                ApiResponse.<State>builder()
	                        .success(true)
	                        .message("State fetched successfully.")
	                        .data(stateService.getById(id))
	                        .build());
	    }

	    @GetMapping("/country/{countryId}")
	    public ResponseEntity<ApiResponse<List<State>>> getByCountry(@PathVariable Long countryId) {

	        return ResponseEntity.ok(
	                ApiResponse.<List<State>>builder()
	                        .success(true)
	                        .message("State list fetched successfully.")
	                        .data(stateService.getByCountry(countryId))
	                        .build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

	        stateService.delete(id);

	        return ResponseEntity.ok(
	                ApiResponse.<Void>builder()
	                        .success(true)
	                        .message("State deleted successfully.")
	                        .build());
	    }
	}


