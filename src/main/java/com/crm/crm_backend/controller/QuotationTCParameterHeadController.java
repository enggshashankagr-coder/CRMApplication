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
import com.crm.crm_backend.entity.QuotationTCParameterHead;
import com.crm.crm_backend.serviceImpl.QuotationTCParameterHeadServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quotation-tc-parameter-heads")
@RequiredArgsConstructor
public class QuotationTCParameterHeadController {

    private final QuotationTCParameterHeadServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<QuotationTCParameterHead>> save(
            @RequestBody QuotationTCParameterHead request){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterHead>builder()
                        .success(true)
                        .message("Quotation Terms Parameter saved successfully.")
                        .data(service.save(request))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuotationTCParameterHead>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<QuotationTCParameterHead>>builder()
                        .success(true)
                        .message("Quotation Terms Parameter list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuotationTCParameterHead>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterHead>builder()
                        .success(true)
                        .message("Quotation Terms Parameter fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<QuotationTCParameterHead>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterHead>builder()
                        .success(true)
                        .message(active
                                ? "Quotation Terms Parameter enabled successfully."
                                : "Quotation Terms Parameter disabled successfully.")
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
                        .message("Quotation Terms Parameter deleted successfully.")
                        .build());

    }

}
