package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "goi_kham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoiKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_goi")
    private Integer maGoi;

    @Column(name = "ten_goi", nullable = false)
    private String tenGoi;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia", nullable = false)
    private BigDecimal gia;

    @Column(name = "thoi_gian_thuc_hien")
    private Integer thoiGianThucHien;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column(name="anh_sp")
    private String anhSp;
} 