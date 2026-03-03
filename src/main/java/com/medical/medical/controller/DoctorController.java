package com.medical.medical.controller;


import com.medical.medical.dto.response.DoctorResponse;
import com.medical.medical.enums.Specialty;
import com.medical.medical.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(
            @RequestParam(required = false) Specialty specialty) {
        if (specialty != null) {
            return ResponseEntity.ok(doctorService.getDoctorsBySpecialty(specialty));
        }
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
}