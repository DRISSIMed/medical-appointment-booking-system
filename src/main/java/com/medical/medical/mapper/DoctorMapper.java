package com.medical.medical.mapper;

import com.medical.medical.dto.response.DoctorResponse;
import com.medical.medical.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "fullName", expression = "java(doctor.getFullName())")
    @Mapping(target = "specialtyLabel", expression = "java(formatSpecialty(doctor.getSpecialty().name()))")
    DoctorResponse toResponse(Doctor doctor);

    List<DoctorResponse> toResponseList(List<Doctor> doctors);

    default String formatSpecialty(String specialty) {
        return specialty.replace("_", " ")
                .toLowerCase()
                .substring(0, 1).toUpperCase()
                + specialty.replace("_", " ").toLowerCase().substring(1);
    }
}
