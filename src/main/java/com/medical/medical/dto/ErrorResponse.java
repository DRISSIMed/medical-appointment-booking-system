package com.medical.medical.dto;

public record ErrorResponse(
        int status,
        String error,
        String message
        ) {
}
