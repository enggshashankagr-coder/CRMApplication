package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ComplaintSource;
import com.crm.crm_backend.serviceImpl.ComplaintSourceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/complaint-sources")
@RequiredArgsConstructor
public class ComplaintSourceController {

    private final ComplaintSourceServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintSource>> save(
            @RequestBody ComplaintSource complaintSource) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ComplaintSource>builder()
                        .success(true)
                        .message("Complaint Source saved successfully.")
                        .data(service.save(complaintSource))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintSource>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<ComplaintSource>>builder()
                        .success(true)
                        .message("Complaint Source list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintSource>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintSource>builder()
                        .success(true)
                        .message("Complaint Source fetched successfully.")
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
                        .message("Complaint Source deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ComplaintSource>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintSource>builder()
                        .success(true)
                        .message(active
                                ? "Complaint Source enabled successfully."
                                : "Complaint Source disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}