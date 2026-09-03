package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.SparePartType;
import com.crm.crm_backend.serviceImpl.SparePartTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/spare-part-types")
@RequiredArgsConstructor
public class SparePartTypeController {

    private final SparePartTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<SparePartType>> save(
            @RequestBody SparePartType request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiResponse.<SparePartType>builder()
                        .success(true)
                        .message(
                            "Spare Part Type saved successfully.")
                        .data(service.save(request))
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SparePartType>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<SparePartType>>builder()
                    .success(true)
                    .message(
                        "Spare Part Type list fetched successfully.")
                    .data(service.getAll())
                    .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SparePartType>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<SparePartType>builder()
                    .success(true)
                    .message(
                        "Spare Part Type fetched successfully.")
                    .data(service.getById(id))
                    .build()
        );
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<SparePartType>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<SparePartType>builder()
                    .success(true)
                    .message(
                        active
                        ? "Spare Part Type enabled successfully."
                        : "Spare Part Type disabled successfully.")
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
                        "Spare Part Type deleted successfully.")
                    .build()
        );
    }
}