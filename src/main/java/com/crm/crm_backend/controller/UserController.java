package com.crm.crm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.crm_backend.DTO.CreateUserRequest;
import com.crm.crm_backend.DTO.UserResponse;
import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.service.UserService;
import com.crm.crm_backend.util.ApiResponseUtil;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management")
public class UserController { 

	@Autowired
    public UserService service;
    
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(
            @Valid @RequestBody CreateUserRequest request){
    	 System.out.println("Username = " + request.getUsername());
    	    System.out.println("Password = " + request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.success(
                        "User created successfully",
                        service.create(request)));

    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll(){

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Success",
                        service.getAll()));

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Success",
                        service.getById(id)));

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id){

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponseUtil.success("Deleted Successfully"));

    }

}
