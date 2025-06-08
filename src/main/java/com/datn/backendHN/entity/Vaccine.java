package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vaccine")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer numberOfDoses;

    @Column(nullable = false)
    private Integer intervalBetweenDoses;

    @Column(nullable = false)
    private String contraindications;

    @Column(nullable = false)
    private String sideEffects;

    @Column(nullable = false)
    private Boolean isActive = true;
} 