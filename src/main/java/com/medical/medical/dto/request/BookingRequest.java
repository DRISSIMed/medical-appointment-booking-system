package com.medical.medical.dto.request;



public record BookingRequest(
        @NotNull(message = "Time slot ID is required")
        Long timeSlotId,

        @NotBlank(message = "Patient name is required")
        String patientName,

        @NotBlank(message = "Patient email is required")
        @Email(message = "Patient email must be valid")
        String patientEmail
) {}