package com.datn.backendHN.entity;

import com.datn.backendHN.enums.TrangThaiDangKy;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dang_ky_goi_kham")
@Data
public class DangKyGoiKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_dang_ky")
    private Integer maDangKy;

    @Column(name = "ma_ho_so")
    private Integer maHoSo;

    @Column(name = "ma_goi")
    private Integer maGoi;

    @Column(name = "ngay_dang_ky")
    private LocalDateTime ngayDangKy;

    @Column(name = "ngay_thuc_hien")
    private LocalDate ngayThucHien;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    private TrangThaiDangKy trangThai;

    @Column(name = "gia_tien")
    private BigDecimal giaTien;

    @Column(name = "gio_kham")
    private String gioKham;

    @Column(name = "user_id")
    private Integer userId;
} 