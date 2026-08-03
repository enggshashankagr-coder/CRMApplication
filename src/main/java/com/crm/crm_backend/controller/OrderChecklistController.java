package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.OrderChecklist;
import com.crm.crm_backend.serviceImpl.OrderChecklistServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-checklists")
@RequiredArgsConstructor
public class OrderChecklistController {

    private final OrderChecklistServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderChecklist>> save(
            @RequestBody OrderChecklist checklist){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<OrderChecklist>builder()
                        .success(true)
                        .message("Order Checklist saved successfully.")
                        .data(service.save(checklist))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderChecklist>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<OrderChecklist>>builder()
                        .success(true)
                        .message("Order Checklist list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderChecklist>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<OrderChecklist>builder()
                        .success(true)
                        .message("Order Checklist fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @GetMapping("/type/{checklistFor}")
    public ResponseEntity<ApiResponse<List<OrderChecklist>>> getByChecklistFor(
            @PathVariable String checklistFor){

        return ResponseEntity.ok(
                ApiResponse.<List<OrderChecklist>>builder()
                        .success(true)
                        .message("Order Checklist list fetched successfully.")
                        .data(service.getByChecklistFor(checklistFor))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<OrderChecklist>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<OrderChecklist>builder()
                        .success(true)
                        .message(active
                                ? "Order Checklist enabled successfully."
                                : "Order Checklist disabled successfully.")
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
                        .message("Order Checklist deleted successfully.")
                        .build());

    }

}