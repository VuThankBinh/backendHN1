package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "thanh_vien")
public class ThanhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "ten_dang_nhap", nullable = false, length = 50, unique = true)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false, length = 255)
    private String matKhau;

    @Column(name = "vai_tro", length = 50)
    private String vaiTro;
} 