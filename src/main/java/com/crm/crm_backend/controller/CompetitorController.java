package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.crm.crm_backend.entity.Competitor;
import com.crm.crm_backend.serviceImpl.CompetitorServiceImpl;

@RestController
@RequestMapping("/api/v1/competitors")
public class CompetitorController {
	
	@Autowired
    private  CompetitorServiceImpl competitorService;

    @PostMapping
    public ResponseEntity<ApiResponse<Competitor>> save(
            @RequestBody Competitor competitor){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Competitor>builder()
                        .success(true)
                        .message("Competitor saved successfully.")
                        .data(competitorService.save(competitor))
                        .build());

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Competitor>>> getAll(){

        return ResponseEntity.ok(
                ApiResponse.<List<Competitor>>builder()
                        .success(true)
                        .message("Competitor list fetched successfully.")
                        .data(competitorService.getAll())
                        .build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Competitor>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<Competitor>builder()
                        .success(true)
                        .message("Competitor fetched successfully.")
                        .data(competitorService.getById(id))
                        .build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id){

        competitorService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Competitor deleted successfully.")
                        .build());

    }
    
    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<Competitor>> changeStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {

        return ResponseEntity.ok(
                ApiResponse.<Competitor>builder()
                        .success(true)
                        .message(active ? "Competitor enabled successfully."
                                        : "Competitor disabled successfully.")
                        .data(competitorService.changeStatus(id, active))
                        .build());
    }
    
}
