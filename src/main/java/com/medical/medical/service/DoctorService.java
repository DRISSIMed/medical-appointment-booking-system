package com.medical.medical.service;

import com.medical.medical.dto.response.DoctorResponse;
import com.medical.medical.enums.Specialty;

import java.util.List;

public interface DoctorService {
    List<DoctorResponse> getAllDoctors();

    List<DoctorResponse> getDoctorsBySpecialty(Specialty specialty);

    DoctorResponse getDoctorById(Long id);
}
