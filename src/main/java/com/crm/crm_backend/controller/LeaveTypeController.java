package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.LeaveType;
import com.crm.crm_backend.serviceImpl.LeaveTypeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {

    private final LeaveTypeServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<LeaveType>> save(
            @RequestBody LeaveType leaveType){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<LeaveType>builder()
                        .success(true)
                        .message("Leave Type saved successfully.")
                        .data(service.save(leaveType))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<LeaveType>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<LeaveType>>builder()
                        .success(true)
                        .message("Leave Type list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LeaveType>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<LeaveType>builder()
                        .success(true)
                        .message("Leave Type fetched successfully.")
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
                        .message("Leave Type deleted successfully.")
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<LeaveType>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<LeaveType>builder()
                        .success(true)
                        .message(active
                                ? "Leave Type enabled successfully."
                                : "Leave Type disabled successfully.")
                        .data(service.changeStatus(id, active))
                        .build());

    }

}