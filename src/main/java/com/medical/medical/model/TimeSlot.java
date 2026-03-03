package com.medical.medical.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "time_slots")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private boolean booked;

    @Column
    private String patientName;

    @Column
    private String patientEmail;

    public boolean isAvailable() {
        return !booked;
    }


    public TimeSlot(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime,boolean booked) {
        this.doctor    = doctor;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.booked    = false;
    }
}
