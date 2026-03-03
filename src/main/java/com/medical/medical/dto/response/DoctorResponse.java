package com.medical.medical.dto.response;

import com.medical.medical.enums.Specialty;

public record DoctorResponse(
        Long id,
        String firstName,
        String lastName,
        String fullName,
        Specialty specialty,
        String specialtyLabel
) {}
