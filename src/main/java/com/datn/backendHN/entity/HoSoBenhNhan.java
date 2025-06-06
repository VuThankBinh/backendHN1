package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ho_so_benh_nhan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoSoBenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ho_so")
    private Integer maHoSo;

    @Column(name = "nguoi_dung_id")
    private Integer nguoiDungId;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "ngay_sinh")
    private String ngaySinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "ma_dinh_danh")
    private String maDinhDanh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "nghe_nghiep")
    private String ngheNghiep;

    @Column(name = "email")
    private String email;

    @Column(name = "noi_thuong_tru")
    private String noiThuongTru;

    @Column(name = "ngay_cap")
    private String ngayCap;
} 