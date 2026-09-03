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

import com.crm.crm_backend.DTO.QuotationTCParameterDetailRequest;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.QuotationTCParameterDetail;
import com.crm.crm_backend.serviceImpl.QuotationTCParameterDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quotation-tc-parameter-details")
@RequiredArgsConstructor
public class QuotationTCParameterDetailController {

    private final QuotationTCParameterDetailServiceImpl service;

    @PostMapping
    public ResponseEntity<ApiResponse<QuotationTCParameterDetail>> save(
            @RequestBody QuotationTCParameterDetailRequest request){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterDetail>builder()
                        .success(true)
                        .message("Parameter Detail saved successfully.")
                        .data(service.save(request))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<QuotationTCParameterDetail>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<QuotationTCParameterDetail>>builder()
                        .success(true)
                        .message("Parameter Detail list fetched successfully.")
                        .data(service.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuotationTCParameterDetail>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterDetail>builder()
                        .success(true)
                        .message("Parameter Detail fetched successfully.")
                        .data(service.getById(id))
                        .build());

    }

    @GetMapping("/parameter-head/{parameterHeadId}")
    public ResponseEntity<ApiResponse<List<QuotationTCParameterDetail>>> getByParameterHead(
            @PathVariable Long parameterHeadId){

        return ResponseEntity.ok(
                ApiResponse.<List<QuotationTCParameterDetail>>builder()
                        .success(true)
                        .message("Parameter Detail list fetched successfully.")
                        .data(service.getByParameterHead(parameterHeadId))
                        .build());

    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<QuotationTCParameterDetail>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active){

        return ResponseEntity.ok(
                ApiResponse.<QuotationTCParameterDetail>builder()
                        .success(true)
                        .message(active
                                ? "Parameter Detail enabled successfully."
                                : "Parameter Detail disabled successfully.")
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
                        .message("Parameter Detail deleted successfully.")
                        .build());

    }

}