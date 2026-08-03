package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.FinancialYear;
import com.crm.crm_backend.serviceImpl.FinancialYearServiceimpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/financial-years")
@RequiredArgsConstructor
public class FinancialYearController {

    private final FinancialYearServiceimpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<FinancialYear>> save(
            @RequestBody FinancialYear financialYear){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<FinancialYear>builder()
                        .success(true)
                        .message("Financial Year saved successfully.")
                        .data(service.save(financialYear))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FinancialYear>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<FinancialYear>>builder()
                        .success(true)
                        .message("Financial Year list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FinancialYear>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<FinancialYear>builder()
                        .success(true)
                        .message("Financial Year fetched successfully.")
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
                        .message("Financial Year deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<FinancialYear>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<FinancialYear>builder()
                        .success(true)
                        .message(active
                                ? "Financial Year enabled successfully."
                                : "Financial Year disabled successfully.")
                        .data(service.changeStatus(id,active))
                        .build());

    }

}