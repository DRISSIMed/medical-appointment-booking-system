package com.medical.medical.service.impl;

import com.medical.medical.dto.request.BookingRequest;
import com.medical.medical.dto.response.BookingResponse;
import com.medical.medical.dto.response.TimeSlotResponse;
import com.medical.medical.exception.ResourceNotFoundException;
import com.medical.medical.exception.SlotAlreadyBookedException;
import com.medical.medical.mapper.TimeSlotMapper;
import com.medical.medical.model.TimeSlot;
import com.medical.medical.repository.DoctorRepository;
import com.medical.medical.repository.TimeSlotRepository;
import com.medical.medical.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@Slf4j
public class BookServiceImpl  implements BookService {

    private final TimeSlotRepository timeSlotRepository;
    private final DoctorRepository doctorRepository;
    private final TimeSlotMapper timeSlotMapper;

    public BookServiceImpl(TimeSlotRepository timeSlotRepository, DoctorRepository doctorRepository, TimeSlotMapper timeSlotMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.doctorRepository = doctorRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    public List<TimeSlotResponse> getAvailableSlots(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + doctorId);
        }
        return timeSlotMapper.toResponseList(
                timeSlotRepository.findAvailableSlotsByDoctorId(doctorId)
        );

    }

    @Override
    public BookingResponse bookSlot(BookingRequest request) {
        TimeSlot slot = timeSlotRepository.findById(request.timeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("TimeSlot  not found with id" + request.timeSlotId()));

        if (!slot.isAvailable()) {
            throw new SlotAlreadyBookedException("Doctor mr " + slot.getDoctor().getFullName()+  " have been already booked" );
        }

        slot.setBooked(true);
        slot.setPatientName(request.patientName());
        slot.setPatientEmail(request.patientEmail());
        timeSlotRepository.save(slot);
        return new BookingResponse(
                slot.getId(),
                slot.getDoctor().getFullName(),
                slot.getPatientName(),
                slot.getPatientEmail(),
                slot.getStartTime(),
                slot.getEndTime(),
                "Your booking has been confirmed with Dr. " + slot.getDoctor().getFullName()
        );

    }
}
