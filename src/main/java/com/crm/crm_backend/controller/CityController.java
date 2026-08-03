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
import com.crm.crm_backend.entity.City;
import com.crm.crm_backend.serviceImpl.CityServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

	@Autowired
    private  CityServiceImpl cityService;

    @PostMapping
    public ResponseEntity<ApiResponse<City>> save(
            @RequestBody City city) {

        City savedCity = cityService.save(city);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<City>builder()
                        .success(true)
                        .message("City saved successfully.")
                        .data(savedCity)
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<City>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<City>>builder()
                        .success(true)
                        .message("City list fetched successfully.")
                        .data(cityService.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<City>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<City>builder()
                        .success(true)
                        .message("City fetched successfully.")
                        .data(cityService.getById(id))
                        .build());

    }

    @GetMapping("/state/{stateId}")
    public ResponseEntity<ApiResponse<List<City>>> getByState(
            @PathVariable Long stateId) {

        return ResponseEntity.ok(
                ApiResponse.<List<City>>builder()
                        .success(true)
                        .message("City list fetched successfully.")
                        .data(cityService.getByState(stateId))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id) {

        cityService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("City deleted successfully.")
                        .build());

    }
}
