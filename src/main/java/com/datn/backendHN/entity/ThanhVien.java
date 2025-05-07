package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thanh_vien")
@Inheritance(strategy = InheritanceType.JOINED)
public class ThanhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "so_dien_thoai", nullable = false, unique = true)
    private String soDienThoai;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "ten_dang_nhap", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "vai_tro")
    private String vaiTro;
} 