package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "goi_kham")
public class GoiKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_goi_kham", nullable = false)
    private String tenGoiKham;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia_tien", nullable = false)
    private BigDecimal giaTien;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chuyen_khoa_id", nullable = false)
    private ChuyenKhoaEntity chuyenKhoa;
} 