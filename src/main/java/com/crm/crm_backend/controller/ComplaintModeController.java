package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ComplaintMode;
import com.crm.crm_backend.serviceImpl.ComplaintModeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/complaint-modes")
@RequiredArgsConstructor
public class ComplaintModeController {

    private final ComplaintModeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintMode>> save(
            @RequestBody ComplaintMode complaintMode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ComplaintMode>builder()
                        .success(true)
                        .message("Complaint Mode saved successfully.")
                        .data(service.save(complaintMode))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintMode>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<ComplaintMode>>builder()
                        .success(true)
                        .message("Complaint Mode list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintMode>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintMode>builder()
                        .success(true)
                        .message("Complaint Mode fetched successfully.")
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
                        .message("Complaint Mode deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ComplaintMode>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintMode>builder()
                        .success(true)
                        .message(active
                                ? "Complaint Mode enabled successfully."
                                : "Complaint Mode disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}