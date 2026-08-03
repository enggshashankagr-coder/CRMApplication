package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ExpenseType;
import com.crm.crm_backend.serviceImpl.ExpenseTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/expense-types")
@RequiredArgsConstructor
public class ExpenseTypeController {

    private final ExpenseTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseType>> save(
            @RequestBody ExpenseType expenseType){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ExpenseType>builder()
                        .success(true)
                        .message("Expense Type saved successfully.")
                        .data(service.save(expenseType))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseType>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<ExpenseType>>builder()
                        .success(true)
                        .message("Expense Type list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseType>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseType>builder()
                        .success(true)
                        .message("Expense Type fetched successfully.")
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
                        .message("Expense Type deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ExpenseType>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseType>builder()
                        .success(true)
                        .message(active
                                ? "Expense Type enabled successfully."
                                : "Expense Type disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}