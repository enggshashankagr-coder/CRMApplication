package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.SparePart;
import com.crm.crm_backend.serviceImpl.SparePartServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/spare-parts")
@RequiredArgsConstructor
public class SparePartController {

    private final SparePartServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<SparePart>> save(
            @RequestBody SparePart request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiResponse.<SparePart>builder()
                        .success(true)
                        .message(
                            "Spare Part saved successfully.")
                        .data(service.save(request))
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SparePart>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<SparePart>>builder()
                    .success(true)
                    .message(
                        "Spare Part list fetched successfully.")
                    .data(service.getAll())
                    .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SparePart>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<SparePart>builder()
                    .success(true)
                    .message(
                        "Spare Part fetched successfully.")
                    .data(service.getById(id))
                    .build()
        );
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<SparePart>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<SparePart>builder()
                    .success(true)
                    .message(
                        active
                        ? "Spare Part enabled successfully."
                        : "Spare Part disabled successfully.")
                    .data(
                        service.changeStatus(id, active))
                    .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                    .success(true)
                    .message(
                        "Spare Part deleted successfully.")
                    .build()
        );
    }
}