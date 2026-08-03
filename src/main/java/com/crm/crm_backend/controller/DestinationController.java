package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.Destination;
import com.crm.crm_backend.serviceImpl.DestinationServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<Destination>> save(
            @RequestBody Destination destination) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Destination>builder()
                        .success(true)
                        .message("Destination saved successfully.")
                        .data(service.save(destination))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Destination>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Destination>>builder()
                        .success(true)
                        .message("Destination list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Destination>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Destination>builder()
                        .success(true)
                        .message("Destination fetched successfully.")
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
                        .message("Destination deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<Destination>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<Destination>builder()
                        .success(true)
                        .message(active
                                ? "Destination enabled successfully."
                                : "Destination disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}