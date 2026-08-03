package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.PaymentMode;
import com.crm.crm_backend.serviceImpl.PaymentModeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment-modes")
@RequiredArgsConstructor
public class PaymentModeController {

    private final PaymentModeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMode>> save(
            @RequestBody PaymentMode paymentMode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<PaymentMode>builder()
                        .success(true)
                        .message("Payment Mode saved successfully.")
                        .data(service.save(paymentMode))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentMode>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<PaymentMode>>builder()
                        .success(true)
                        .message("Payment Mode list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMode>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<PaymentMode>builder()
                        .success(true)
                        .message("Payment Mode fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<PaymentMode>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<PaymentMode>builder()
                        .success(true)
                        .message(active
                                ? "Payment Mode enabled successfully."
                                : "Payment Mode disabled successfully.")
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
                        .message("Payment Mode deleted successfully.")
                        .build());

    }

}