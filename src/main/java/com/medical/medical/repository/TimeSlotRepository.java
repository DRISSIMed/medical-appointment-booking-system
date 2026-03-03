package com.medical.medical.repository;

import com.medical.medical.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Query("SELECT ts FROM TimeSlot ts WHERE ts.doctor.id = :doctorId AND ts.booked = false ORDER BY ts.startTime ASC")
    List<TimeSlot> findAvailableSlotsByDoctorId(@Param("doctorId") Long doctorId);

    @Query("SELECT ts FROM TimeSlot ts WHERE ts.doctor.id = :doctorId ORDER BY ts.startTime ASC")
    List<TimeSlot> findAllByDoctorId(@Param("doctorId") Long doctorId);
}