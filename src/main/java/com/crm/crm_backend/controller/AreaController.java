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
import com.crm.crm_backend.entity.Area;
import com.crm.crm_backend.serviceImpl.AreaServiceImpl;

@RestController
@RequestMapping("/api/v1/areas")
public class AreaController {
	
	@Autowired
    private AreaServiceImpl areaService;

    @PostMapping
    public ResponseEntity<ApiResponse<Area>> save(@RequestBody Area area) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Area>builder()
                        .success(true)
                        .message("Area saved successfully.")
                        .data(areaService.save(area))
                        .build()); 
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Area>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.<List<Area>>builder()
                        .success(true)
                        .message("Area list fetched successfully.")
                        .data(areaService.getAll())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Area>> getById(@PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.<Area>builder()
                        .success(true)
                        .message("Area fetched successfully.")
                        .data(areaService.getById(id))
                        .build());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<ApiResponse<List<Area>>> getByCity(@PathVariable Long cityId) {

        return ResponseEntity.ok(
                ApiResponse.<List<Area>>builder()
                        .success(true)
                        .message("Area list fetched successfully.")
                        .data(areaService.getByCity(cityId))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {

        areaService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Area deleted successfully.")
                        .build());
    }
    
}
