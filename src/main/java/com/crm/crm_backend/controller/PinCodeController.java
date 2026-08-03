package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.PinCode;
import com.crm.crm_backend.serviceImpl.PinCodeServiceImpl;

@RestController
@RequestMapping("/api/v1/pincodes")

public class PinCodeController {

	@Autowired
    private  PinCodeServiceImpl pincodeService;

    @PostMapping
    public ResponseEntity<ApiResponse<PinCode>> save(@RequestBody PinCode pincode) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<PinCode>builder()
                        .success(true)
                        .message("Pincode saved successfully.")
                        .data(pincodeService.save(pincode))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PinCode>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<PinCode>>builder()
                        .success(true)
                        .message("Pincode list fetched successfully.")
                        .data(pincodeService.getAll())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PinCode>> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<PinCode>builder()
                        .success(true)
                        .message("Pincode fetched successfully.")
                        .data(pincodeService.getById(id))
                        .build());
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<ApiResponse<List<PinCode>>> getByArea(@PathVariable Long areaId) {

        return ResponseEntity.ok(
                ApiResponse.<List<PinCode>>builder()
                        .success(true)
                        .message("Pincode list fetched successfully.")
                        .data(pincodeService.getByArea(areaId))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        pincodeService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Pincode deleted successfully.")
                        .build());
    }
}