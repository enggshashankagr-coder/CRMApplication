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

import com.crm.crm_backend.DTO.EmployeeRequest;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.Employee;
import com.crm.crm_backend.serviceImpl.EmployeeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl service;


    // =========================================================
    // CREATE / UPDATE EMPLOYEE + IMAGE
    // =========================================================

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Employee>> save(
            @ModelAttribute EmployeeRequest request) {

        Employee employee =
                service.save(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ApiResponse.<Employee>builder()
                                .success(true)
                                .message(
                                        "Employee saved successfully.")
                                .data(employee)
                                .build()
                );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Employee>>builder()
                        .success(true)
                        .message(
                                "Employee list fetched successfully.")
                        .data(
                                service.getAll())
                        .build()
        );
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Employee>builder()
                        .success(true)
                        .message(
                                "Employee fetched successfully.")
                        .data(
                                service.getById(id))
                        .build()
        );
    }


    // =========================================================
    // CHANGE STATUS
    // =========================================================

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<Employee>> changeStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                ApiResponse.<Employee>builder()
                        .success(true)
                        .message(
                                "Employee status updated successfully.")
                        .data(
                                service.changeStatus(
                                        id,
                                        status))
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
                                "Employee deleted successfully.")
                        .build()
        );
    }
}