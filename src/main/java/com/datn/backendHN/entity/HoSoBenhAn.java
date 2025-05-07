package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ho_so_benh_an")
@EqualsAndHashCode(callSuper = false)
public class HoSoBenhAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "benh_nhan_id")
    private BenhNhan benhNhan;

    @ManyToOne
    @JoinColumn(name = "bac_si_id")
    private BacSi bacSi;

    @Column(name = "ngay_kham")
    private LocalDateTime ngayKham;

    @Column(name = "chan_doan")
    private String chanDoan;

    @Column(name = "dieu_tri")
    private String dieuTri;

    @Column(name = "ghi_chu")
    private String ghiChu;
} 