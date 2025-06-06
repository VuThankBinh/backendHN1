package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lich_bac_si")
public class LichBacSiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lich_bac_si")
    private Integer idLichBacSi;

    @ManyToOne
    @JoinColumn(name = "id_bac_si")
    private BacSiEntity bacSi;

    @Column(name = "thu")
    private String thu;

    @Column(name = "gio_bat_dau")
    private LocalTime gioBatDau;

    @Column(name = "gio_ket_thuc")
    private LocalTime gioKetThuc;

    @Column(name = "dang_kha_dung")
    private Boolean dangKhaDung;

    @Column(name = "la_lich_lap")
    private Boolean laLichLap;

    @Column(name = "cac_ngay_lap")
    private String cacNgayLap;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;
} 