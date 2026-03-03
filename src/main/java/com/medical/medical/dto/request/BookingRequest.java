package com.medical.medical.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingRequest(
        @NotNull(message = "Time slot ID is required")
        Long timeSlotId,

        @NotBlank(message = "Patient name is required")
        String patientName,

        @NotBlank(message = "Patient email is required")
        @Email(message = "Patient email must be valid")
        String patientEmail
) {}