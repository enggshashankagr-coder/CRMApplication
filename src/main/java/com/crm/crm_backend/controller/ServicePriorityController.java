package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ServicePriority;
import com.crm.crm_backend.serviceImpl.ServicePriorityServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/service-priorities")
@RequiredArgsConstructor
public class ServicePriorityController {

    private final ServicePriorityServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ServicePriority>> save(
            @RequestBody ServicePriority request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ServicePriority>builder()
                        .success(true)
                        .message("Service Priority saved successfully.")
                        .data(service.save(request))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServicePriority>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<ServicePriority>>builder()
                        .success(true)
                        .message("Service Priority list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServicePriority>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<ServicePriority>builder()
                        .success(true)
                        .message("Service Priority fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ServicePriority>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<ServicePriority>builder()
                        .success(true)
                        .message(active
                                ? "Service Priority enabled successfully."
                                : "Service Priority disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Service Priority deleted successfully.")
                        .build());

    }

}
