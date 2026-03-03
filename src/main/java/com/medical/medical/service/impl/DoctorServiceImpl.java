package com.medical.medical.service.impl;

import com.medical.medical.dto.response.DoctorResponse;
import com.medical.medical.enums.Specialty;
import com.medical.medical.exception.ResourceNotFoundException;
import com.medical.medical.mapper.DoctorMapper;
import com.medical.medical.model.Doctor;
import com.medical.medical.repository.DoctorRepository;
import com.medical.medical.service.DoctorService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Slf4j
@Transactional(readOnly = true)
public class DoctorServiceImpl  implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {
        log.debug("Fetching all doctors");
        return doctorMapper.toResponseList(doctorRepository.findAll());

    }

    @Override
    public List<DoctorResponse> getDoctorsBySpecialty(Specialty specialty) {
        log.debug("Fetching doctors with specialty: {}", specialty);
        return doctorMapper.toResponseList(doctorRepository.findBySpecialty(specialty));

    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        log.debug("Fetching doctor with id: {}", id);
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctorMapper.toResponse(doctor);
    }
}
