package com.medical.medical.config;

import com.medical.medical.enums.Specialty;
import com.medical.medical.model.Doctor;
import com.medical.medical.model.TimeSlot;
import com.medical.medical.repository.DoctorRepository;
import com.medical.medical.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DoctorRepository doctorRepository;
    private final TimeSlotRepository timeSlotRepository;

    public DataInitializer(DoctorRepository doctorRepository, TimeSlotRepository timeSlotRepository) {
        this.doctorRepository = doctorRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public void run(String... args) {


        Doctor drSalhi = createDoctor("Ahmed", "Salhi", Specialty.CARDIOLOGY);
        Doctor drBenali  = createDoctor("Karim",  "Benali",  Specialty.GENERAL_PRACTICE);
        Doctor drAhmadi   = createDoctor("Asmae",    "Ahmadi",    Specialty.DERMATOLOGY);
        Doctor drBayane  = createDoctor("Hassan", "Bayane",  Specialty.NEUROLOGY);

        doctorRepository.saveAll(List.of(drSalhi, drBenali, drAhmadi, drBayane));

        generateSlots(drSalhi);
        generateSlots(drBenali);
        generateSlots(drAhmadi);
        generateSlots(drBayane);

    }

    private Doctor createDoctor(String firstName, String lastName, Specialty specialty) {
        return new Doctor(firstName,lastName,specialty);
    }

    private void generateSlots(Doctor doctor) {
        LocalDateTime base = LocalDateTime.now()
                .plusDays(1)
                .truncatedTo(ChronoUnit.HOURS)
                .withHour(9);

        for (int day = 0; day < 5; day++) {
            for (int slot = 0; slot < 4; slot++) {
                LocalDateTime start = base.plusDays(day).plusMinutes(slot * 30L);
                timeSlotRepository.save(new TimeSlot(doctor,start,start.plusMinutes(30),false));
            }
        }
    }
}
