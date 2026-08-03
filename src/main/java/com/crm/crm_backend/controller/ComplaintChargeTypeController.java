package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ComplaintChargeType;
import com.crm.crm_backend.serviceImpl.ComplaintChargeTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/complaint-charge-types")
@RequiredArgsConstructor
public class ComplaintChargeTypeController {

    private final ComplaintChargeTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintChargeType>> save(
            @RequestBody ComplaintChargeType chargeType) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ComplaintChargeType>builder()
                        .success(true)
                        .message("Complaint Charge Type saved successfully.")
                        .data(service.save(chargeType))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintChargeType>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<ComplaintChargeType>>builder()
                        .success(true)
                        .message("Complaint Charge Type list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintChargeType>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintChargeType>builder()
                        .success(true)
                        .message("Complaint Charge Type fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Complaint Charge Type deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ComplaintChargeType>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintChargeType>builder()
                        .success(true)
                        .message(active
                                ? "Complaint Charge Type enabled successfully."
                                : "Complaint Charge Type disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}