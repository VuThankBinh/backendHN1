package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bac_si")
@PrimaryKeyJoinColumn(name = "id")
public class BacSi extends NhanVien {
    @ManyToOne
    @JoinColumn(name = "chuyen_khoa_id", nullable = false)
    private ChuyenKhoaEntity chuyenKhoa;

    @ManyToOne
    @JoinColumn(name = "phong_kham_id", nullable = false)
    private PhongKham phongKham;

    @Column(name = "bang_cap")
    private String bangCap;

    @Column(name = "kinh_nghiem")
    private Integer kinhNghiem;

} 