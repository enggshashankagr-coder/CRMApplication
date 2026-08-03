package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.LibraryFileSubCategory;
import com.crm.crm_backend.serviceImpl.LibraryFileSubCategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/library-file-sub-categories")
@RequiredArgsConstructor
public class LibraryFileSubCategoryController {

    private final LibraryFileSubCategoryServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<LibraryFileSubCategory>> save(
            @RequestBody LibraryFileSubCategory subCategory){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<LibraryFileSubCategory>builder()
                        .success(true)
                        .message("Library File Sub Category saved successfully.")
                        .data(service.save(subCategory))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LibraryFileSubCategory>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<LibraryFileSubCategory>>builder()
                        .success(true)
                        .message("Library File Sub Category list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LibraryFileSubCategory>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<LibraryFileSubCategory>builder()
                        .success(true)
                        .message("Library File Sub Category fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<LibraryFileSubCategory>>> getByCategory(
            @PathVariable Long categoryId){

        return ResponseEntity.ok(
                ApiResponse.<List<LibraryFileSubCategory>>builder()
                        .success(true)
                        .message("Library File Sub Category list fetched successfully.")
                        .data(service.getByCategory(categoryId))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Library File Sub Category deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<LibraryFileSubCategory>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<LibraryFileSubCategory>builder()
                        .success(true)
                        .message(active
                                ? "Library File Sub Category enabled successfully."
                                : "Library File Sub Category disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}