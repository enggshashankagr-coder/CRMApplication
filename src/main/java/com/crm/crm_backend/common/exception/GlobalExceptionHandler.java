package com.crm.crm_backend.common.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crm.crm_backend.common.ApiResponse;
import com.crm.crm_backend.util.ApiResponseUtil;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException ex) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseUtil.failure(ex.getMessage(), null));

	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponse<?>> handleDuplicate(DuplicateResourceException ex) {

		return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponseUtil.failure(ex.getMessage(), null));

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> validation(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.toList();

		return ResponseEntity.badRequest().body(ApiResponseUtil.failure("Validation Failed", errors));

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> exception(Exception ex) {

		return ResponseEntity.internalServerError().body(ApiResponseUtil.failure(ex.getMessage(), null));

	}

}