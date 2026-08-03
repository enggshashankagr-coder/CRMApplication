package com.crm.crm_backend.common;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

	private boolean success;

	private String message;

	private T data;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public Object getErrors() {
		return errors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	private Object errors;

	@Builder.Default
	private final LocalDateTime timestamp = LocalDateTime.now();

}