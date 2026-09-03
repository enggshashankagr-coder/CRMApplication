package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.ProductSubType;
import com.crm.crm_backend.serviceImpl.ProductSubTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/product-sub-types")
@RequiredArgsConstructor
public class ProductSubTypeController {

    private final ProductSubTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductSubType>> save(
            @RequestBody ProductSubType productSubType){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ProductSubType>builder()
                        .success(true)
                        .message("Product Sub Type saved successfully.")
                        .data(service.save(productSubType))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductSubType>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<ProductSubType>>builder()
                        .success(true)
                        .message("Product Sub Type list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductSubType>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<ProductSubType>builder()
                        .success(true)
                        .message("Product Sub Type fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @GetMapping("/product-type/{productTypeId}")
    public ResponseEntity<ApiResponse<List<ProductSubType>>> getByProductType(
            @PathVariable Long productTypeId){

        return ResponseEntity.ok(
                ApiResponse.<List<ProductSubType>>builder()
                        .success(true)
                        .message("Product Sub Type list fetched successfully.")
                        .data(service.getByProductType(productTypeId))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<ProductSubType>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<ProductSubType>builder()
                        .success(true)
                        .message(active
                                ? "Product Sub Type enabled successfully."
                                : "Product Sub Type disabled successfully.")
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
                        .message("Product Sub Type deleted successfully.")
                        .build());

    }

}