package com.medical.medical.mapper;

import com.medical.medical.dto.response.TimeSlotResponse;
import com.medical.medical.model.TimeSlot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSlotMapper {

    @Mapping(target = "doctorId", expression = "java(timeSlot.getDoctor().getId())")
    TimeSlotResponse toResponse(TimeSlot timeSlot);

    List<TimeSlotResponse> toResponseList(List<TimeSlot> timeSlots);
}