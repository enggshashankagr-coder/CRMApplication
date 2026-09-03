package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.CompanyRequest;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.Company;
import com.crm.crm_backend.serviceImpl.CompanyServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl service;


    // =========================================================
    // CREATE / UPDATE COMPANY + IMAGE
    // =========================================================

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Company>> save(
            @ModelAttribute CompanyRequest request) {

        Company company =
                service.save(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<Company>builder()
                                .success(true)
                                .message(
                                        "Company saved successfully.")
                                .data(company)
                                .build()
                );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Company>>builder()
                        .success(true)
                        .message(
                                "Company list fetched successfully.")
                        .data(
                                service.getAll())
                        .build()
        );
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Company>builder()
                        .success(true)
                        .message(
                                "Company fetched successfully.")
                        .data(
                                service.getById(id))
                        .build()
        );
    }


    // =========================================================
    // ENABLE / DISABLE
    // =========================================================

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<Company>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<Company>builder()
                        .success(true)
                        .message(
                                active
                                        ? "Company enabled successfully."
                                        : "Company disabled successfully.")
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
                                "Company deleted successfully.")
                        .build()
        );
    }
}