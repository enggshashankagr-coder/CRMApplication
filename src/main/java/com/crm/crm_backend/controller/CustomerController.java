package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.CustomerRequest;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.Customer;
import com.crm.crm_backend.serviceImpl.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl service;


    // =========================================================
    // CREATE / UPDATE
    // =========================================================

    @PostMapping
    public ResponseEntity<ApiResponse<Customer>> save(
            @RequestBody CustomerRequest request) {

        Customer customer =
                service.save(request);

        return ResponseEntity.ok(
                ApiResponse.<Customer>builder()
                        .success(true)
                        .message(
                                "Customer saved successfully.")
                        .data(customer)
                        .build()
        );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Customer>>builder()
                        .success(true)
                        .message(
                                "Customer list fetched successfully.")
                        .data(
                                service.getAll())
                        .build()
        );
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Customer>builder()
                        .success(true)
                        .message(
                                "Customer fetched successfully.")
                        .data(
                                service.getById(id))
                        .build()
        );
    }


    // =========================================================
    // ENABLE / DISABLE
    // =========================================================

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<Customer>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<Customer>builder()
                        .success(true)
                        .message(
                                active
                                        ? "Customer enabled successfully."
                                        : "Customer disabled successfully.")
                        .data(
                                service.changeStatus(
                                        id,
                                        active))
                        .build()
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message(
                                "Customer deleted successfully.")
                        .build()
        );
    }
}