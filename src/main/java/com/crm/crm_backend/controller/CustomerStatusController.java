package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.CustomerStatus;
import com.crm.crm_backend.serviceImpl.CustomerStatusServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer-status")
@RequiredArgsConstructor
public class CustomerStatusController {

    private final CustomerStatusServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerStatus>> save(
            @RequestBody CustomerStatus customerStatus){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CustomerStatus>builder()
                        .success(true)
                        .message("Customer Status saved successfully.")
                        .data(service.save(customerStatus))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerStatus>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<CustomerStatus>>builder()
                        .success(true)
                        .message("Customer Status list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerStatus>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<CustomerStatus>builder()
                        .success(true)
                        .message("Customer Status fetched successfully.")
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
                        .message("Customer Status deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<CustomerStatus>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<CustomerStatus>builder()
                        .success(true)
                        .message(active
                                ? "Customer Status enabled successfully."
                                : "Customer Status disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}