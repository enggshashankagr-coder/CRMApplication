package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ExpenseHead;
import com.crm.crm_backend.serviceImpl.ExpenseHeadServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/expense-heads")
@RequiredArgsConstructor
public class ExpenseHeadController {

    private final ExpenseHeadServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseHead>> save(
            @RequestBody ExpenseHead expenseHead){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ExpenseHead>builder()
                        .success(true)
                        .message("Expense Head saved successfully.")
                        .data(service.save(expenseHead))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseHead>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<ExpenseHead>>builder()
                        .success(true)
                        .message("Expense Head list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseHead>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseHead>builder()
                        .success(true)
                        .message("Expense Head fetched successfully.")
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
                        .message("Expense Head deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ExpenseHead>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseHead>builder()
                        .success(true)
                        .message(active
                                ? "Expense Head enabled successfully."
                                : "Expense Head disabled successfully.")
                        .data(service.changeStatus(id,active))
                        .build());

    }

}