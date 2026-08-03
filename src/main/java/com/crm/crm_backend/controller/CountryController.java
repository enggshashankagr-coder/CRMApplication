package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.entity.Country;
import com.crm.crm_backend.serviceImpl.CountryServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

	@Autowired
    private  CountryServiceImpl countryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Country>> save(@RequestBody Country country) {

        Country savedCountry = countryService.save(country);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Country>builder()
                        .success(true)
                        .message("Country saved successfully.")
                        .data(savedCountry)
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Country>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Country>>builder()
                        .success(true)
                        .message("Country list fetched successfully.")
                        .data(countryService.getAll())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Country>> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Country>builder()
                        .success(true)
                        .message("Country fetched successfully.")
                        .data(countryService.getById(id))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        countryService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Country deleted successfully.")
                        .build());
    }


}
