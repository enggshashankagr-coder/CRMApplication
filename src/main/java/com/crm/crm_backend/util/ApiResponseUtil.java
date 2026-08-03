package com.crm.crm_backend.util;

import com.crm.crm_backend.common.ApiResponse;

public final class ApiResponseUtil {

    private ApiResponseUtil() {}

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse<Void> success(String message) {

        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();
    }

    public static ApiResponse<Object> failure(String message, Object errors) {

        return ApiResponse.<Object>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}