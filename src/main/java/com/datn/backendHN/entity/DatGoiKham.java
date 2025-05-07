package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dat_goi_kham")
@EqualsAndHashCode(callSuper = false)
public class DatGoiKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "benh_nhan_id")
    private BenhNhan benhNhan;

    @Column(name = "ten_goi_kham")
    private String tenGoiKham;

    @Column(name = "ngay_dat")
    private LocalDateTime ngayDat;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "ghi_chu")
    private String ghiChu;
} 