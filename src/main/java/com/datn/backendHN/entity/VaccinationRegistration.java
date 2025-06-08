package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_registration")
@Data
public class VaccinationRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
    private VaccinationCalendar calendar;

    @Column(name = "ma_ho_so", nullable = false)
    private Integer maHoSo;

    @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String notes;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
} 