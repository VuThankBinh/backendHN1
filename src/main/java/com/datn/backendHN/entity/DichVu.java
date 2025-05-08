package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dich_vu")
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_dich_vu", nullable = false)
    private String tenDichVu;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "don_vi_tinh", nullable = false)
    private String donViTinh;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "loai_dich_vu", nullable = false)
    private String loaiDichVu; // XÉT_NGHIỆM, KHÁM_BỆNH, TIÊM_CHỦNG, etc.

    @Column(name = "trang_thai", nullable = false)
    private String trangThai; // HOẠT_ĐỘNG, NGỪNG_HOẠT_ĐỘNG
} 