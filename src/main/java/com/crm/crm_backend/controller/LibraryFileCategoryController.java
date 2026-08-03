package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.LibraryFileCategory;
import com.crm.crm_backend.serviceImpl.LibraryFileCategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/library-file-categories")
@RequiredArgsConstructor
public class LibraryFileCategoryController {

    private final LibraryFileCategoryServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<LibraryFileCategory>> save(
            @RequestBody LibraryFileCategory category){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<LibraryFileCategory>builder()
                        .success(true)
                        .message("Library File Category saved successfully.")
                        .data(service.save(category))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LibraryFileCategory>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<LibraryFileCategory>>builder()
                        .success(true)
                        .message("Library File Category list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LibraryFileCategory>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<LibraryFileCategory>builder()
                        .success(true)
                        .message("Library File Category fetched successfully.")
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
                        .message("Library File Category deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<LibraryFileCategory>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<LibraryFileCategory>builder()
                        .success(true)
                        .message(active
                                ? "Library File Category enabled successfully."
                                : "Library File Category disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}