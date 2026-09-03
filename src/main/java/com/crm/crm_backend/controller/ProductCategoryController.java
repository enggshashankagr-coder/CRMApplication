package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ProductCategory;
import com.crm.crm_backend.serviceImpl.ProductCategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductCategory>> save(
            @RequestBody ProductCategory category) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ProductCategory>builder()
                        .success(true)
                        .message("Product Category saved successfully.")
                        .data(service.save(category))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductCategory>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<ProductCategory>>builder()
                        .success(true)
                        .message("Product Category list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductCategory>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<ProductCategory>builder()
                        .success(true)
                        .message("Product Category fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ProductCategory>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<ProductCategory>builder()
                        .success(true)
                        .message(active
                                ? "Product Category enabled successfully."
                                : "Product Category disabled successfully.")
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
                        .message("Product Category deleted successfully.")
                        .build());

    }

}