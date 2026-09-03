package com.crm.crm_backend.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.OrderLostReason;
import com.crm.crm_backend.serviceImpl.OrderLostReasonServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-lost-reasons")
@RequiredArgsConstructor
public class OrderLostReasonController {

    private final OrderLostReasonServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderLostReason>> save(
            @RequestBody OrderLostReason request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<OrderLostReason>builder()
                        .success(true)
                        .message("Order Lost Reason saved successfully.")
                        .data(service.save(request))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderLostReason>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<OrderLostReason>>builder()
                        .success(true)
                        .message("Order Lost Reason list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderLostReason>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<OrderLostReason>builder()
                        .success(true)
                        .message("Order Lost Reason fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<OrderLostReason>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<OrderLostReason>builder()
                        .success(true)
                        .message(active
                                ? "Order Lost Reason enabled successfully."
                                : "Order Lost Reason disabled successfully.")
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
                        .message("Order Lost Reason deleted successfully.")
                        .build());

    }

}