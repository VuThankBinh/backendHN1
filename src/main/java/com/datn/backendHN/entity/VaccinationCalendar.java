package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "vaccination_calendar")
public class VaccinationCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(name = "vaccination_date", nullable = false)
    private LocalDate vaccinationDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "max_patients", nullable = false)
    private Integer maxPatients;

    @Column(name = "current_patients", nullable = false)
    private Integer currentPatients = 0;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
} 