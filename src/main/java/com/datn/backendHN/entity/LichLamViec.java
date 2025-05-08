package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lich_lam_viec")
public class LichLamViec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bac_si_id", nullable = false)
    private Integer bacSiId;

    @Column(name = "ngay", nullable = false)
    private LocalDate ngay;

    @Column(name = "gio_bat_dau", nullable = false)
    private LocalTime gioBatDau;

    @Column(name = "gio_ket_thuc", nullable = false)
    private LocalTime gioKetThuc;

    @ManyToOne
    @JoinColumn(name = "bac_si_id", insertable = false, updatable = false)
    private BacSi bacSi;
} 