package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.PaymentPlan;
import com.crm.crm_backend.serviceImpl.PaymentPlanServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment-plans")
@RequiredArgsConstructor
public class PaymentPlanController {

    private final PaymentPlanServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentPlan>> save(
            @RequestBody PaymentPlan paymentPlan) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<PaymentPlan>builder()
                        .success(true)
                        .message("Payment Plan saved successfully.")
                        .data(service.save(paymentPlan))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentPlan>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<PaymentPlan>>builder()
                        .success(true)
                        .message("Payment Plan list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentPlan>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<PaymentPlan>builder()
                        .success(true)
                        .message("Payment Plan fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<PaymentPlan>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<PaymentPlan>builder()
                        .success(true)
                        .message(active
                                ? "Payment Plan enabled successfully."
                                : "Payment Plan disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Payment Plan deleted successfully.")
                        .build());

    }

}