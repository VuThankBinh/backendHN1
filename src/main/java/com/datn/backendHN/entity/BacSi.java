package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bac_si")
public class BacSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tai_khoan_id", nullable = false)
    private Integer taiKhoanId;


    @Column(name = "ten_chuc_vu", nullable = false)
    private String tenChucVu;

    @Column(name = "chuyen_khoa_id", nullable = false)
    private Integer chuyenKhoaId;

    @Column(name = "phong_kham_id", nullable = false)
    private Integer phongKhamId;

    @Column(name = "bang_cap")
    private String bangCap;

    @Column(name = "kinh_nghiem")
    private Integer kinhNghiem;
} 