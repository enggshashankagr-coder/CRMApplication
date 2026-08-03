package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ComplaintCategory;
import com.crm.crm_backend.serviceImpl.ComplaintCategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
	@RequestMapping("/api/v1/complaint-categories")
public class ComplaintCategoryController {

		@Autowired
	    private  ComplaintCategoryServiceImpl service;

	    @PostMapping
	    public ResponseEntity<ApiResponse<ComplaintCategory>> save(
	            @RequestBody ComplaintCategory category){

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(ApiResponse.<ComplaintCategory>builder()
	                        .success(true)
	                        .message("Complaint Category saved successfully.")
	                        .data(service.save(category))
	                        .build());

	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse<List<ComplaintCategory>>> getAll(){

	        return ResponseEntity.ok(
	                ApiResponse.<List<ComplaintCategory>>builder()
	                        .success(true)
	                        .message("Complaint Category list fetched successfully.")
	                        .data(service.getAll())
	                        .build());

	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<ComplaintCategory>> getById(
	            @PathVariable Long id){

	        return ResponseEntity.ok(
	                ApiResponse.<ComplaintCategory>builder()
	                        .success(true)
	                        .message("Complaint Category fetched successfully.")
	                        .data(service.getById(id))
	                        .build());

	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<Void>> delete(
	            @PathVariable Long id){

	        service.delete(id);

	        return ResponseEntity.ok(
	                ApiResponse.<Void>builder()
	                        .success(true)
	                        .message("Complaint Category deleted successfully.")
	                        .build());

	    }

	    @PutMapping("/status/{id}")
	    public ResponseEntity<ApiResponse<ComplaintCategory>> changeStatus(
	            @PathVariable Long id,
	            @RequestParam Boolean active){

	        return ResponseEntity.ok(
	                ApiResponse.<ComplaintCategory>builder()
	                        .success(true)
	                        .message(active
	                                ? "Complaint Category enabled successfully."
	                                : "Complaint Category disabled successfully.")
	                        .data(service.changeStatus(id, active))
	                        .build());

	    }
	}
