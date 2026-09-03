package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.PreventiveMaintenance;
import com.crm.crm_backend.serviceImpl.PreventiveMaintenanceServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/preventive-maintenances")
@RequiredArgsConstructor
public class PreventiveMaintenanceController {

    private final PreventiveMaintenanceServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<PreventiveMaintenance>> save(
            @RequestBody PreventiveMaintenance maintenance){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<PreventiveMaintenance>builder()
                        .success(true)
                        .message("Preventive Maintenance saved successfully.")
                        .data(service.save(maintenance))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PreventiveMaintenance>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<PreventiveMaintenance>>builder()
                        .success(true)
                        .message("Preventive Maintenance list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PreventiveMaintenance>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<PreventiveMaintenance>builder()
                        .success(true)
                        .message("Preventive Maintenance fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<PreventiveMaintenance>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<PreventiveMaintenance>builder()
                        .success(true)
                        .message(active
                                ? "Preventive Maintenance enabled successfully."
                                : "Preventive Maintenance disabled successfully.")
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
                        .message("Preventive Maintenance deleted successfully.")
                        .build());

    }

}