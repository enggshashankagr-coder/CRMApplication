package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ComplaintNature;
import com.crm.crm_backend.serviceImpl.ComplaintNatureServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/complaint-natures")
@RequiredArgsConstructor
public class ComplaintNatureController {

    private final ComplaintNatureServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintNature>> save(
            @RequestBody ComplaintNature complaintNature) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ComplaintNature>builder()
                        .success(true)
                        .message("Complaint Nature saved successfully.")
                        .data(service.save(complaintNature))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintNature>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<ComplaintNature>>builder()
                        .success(true)
                        .message("Complaint Nature list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintNature>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintNature>builder()
                        .success(true)
                        .message("Complaint Nature fetched successfully.")
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
                        .message("Complaint Nature deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ComplaintNature>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<ComplaintNature>builder()
                        .success(true)
                        .message(active
                                ? "Complaint Nature enabled successfully."
                                : "Complaint Nature disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}