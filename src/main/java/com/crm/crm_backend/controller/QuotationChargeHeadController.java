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

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.QuotationChargeHead;
import com.crm.crm_backend.serviceImpl.QuotationChargeHeadServiceimpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quotation-charge-heads")
@RequiredArgsConstructor
public class QuotationChargeHeadController {

    private final QuotationChargeHeadServiceimpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<QuotationChargeHead>> save(
            @RequestBody QuotationChargeHead request){

        return ResponseEntity.ok(
                ApiResponse.<QuotationChargeHead>builder()
                        .success(true)
                        .message("Quotation Charge saved successfully.")
                        .data(service.save(request))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuotationChargeHead>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<QuotationChargeHead>>builder()
                        .success(true)
                        .message("Quotation Charge list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuotationChargeHead>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<QuotationChargeHead>builder()
                        .success(true)
                        .message("Quotation Charge fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<QuotationChargeHead>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<QuotationChargeHead>builder()
                        .success(true)
                        .message(active
                                ? "Quotation Charge enabled successfully."
                                : "Quotation Charge disabled successfully.")
                        .data(service.changeStatus(id,active))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Quotation Charge deleted successfully.")
                        .build());

    }

}