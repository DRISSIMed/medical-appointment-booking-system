package com.medical.medical.dto.response;


import java.time.LocalDateTime;

public record TimeSlotResponse(
        Long id,
        Long doctorId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        boolean booked
) {}