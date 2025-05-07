package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lich_tiem")
@EqualsAndHashCode(callSuper = false)
public class LichTiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "benh_nhan_id")
    private BenhNhan benhNhan;

    @Column(name = "ten_vaccine")
    private String tenVaccine;

    @Column(name = "ngay_tiem")
    private LocalDateTime thoiGianTiem;


    @Column(name = "dia_diem")
    private String diaDiem;
} 