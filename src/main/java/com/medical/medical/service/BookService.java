package com.medical.medical.service;

import com.medical.medical.dto.request.BookingRequest;
import com.medical.medical.dto.response.BookingResponse;
import com.medical.medical.dto.response.TimeSlotResponse;


import java.util.List;

public interface BookService {
    List<TimeSlotResponse> getAvailableSlots(Long doctorId);
     BookingResponse bookSlot(BookingRequest request);
}
