package com.medical.medical.model;

import com.medical.medical.enums.Specialty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TimeSlot> timeSlots = new ArrayList<>();

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public Doctor(String firstName, String lastName, Specialty specialty) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.specialty = specialty;
    }
}

