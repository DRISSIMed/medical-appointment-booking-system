package com.medical.medical.dto.response;
import java.time.LocalDateTime;

public record BookingResponse(
        Long timeSlotId,
        String doctorFullName,
        String patientName,
        String patientEmail,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String confirmationMessage
) {}
