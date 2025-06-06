package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goi_kham_truc_tiep")
public class GoiKhamTrucTiepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_goi_kham")
    private Integer id;

    @Column(name = "ten_goi_kham", nullable = false)
    private String tenGoiKham;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia_tien", nullable = false)
    private BigDecimal giaTien;

    @ManyToOne
    @JoinColumn(name = "id_chuyen_khoa", nullable = false)
    private ChuyenKhoaEntity chuyenKhoa;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai;

    @Column(name = "thoi_gian_kham")
    private Integer thoiGianKham; // thời gian khám tính bằng phút
} 