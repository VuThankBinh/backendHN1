package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "dich_vu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DichVuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dich_vu")
    private Integer idDichVu;

    @Column(name = "ten_dich_vu", nullable = false, length = 100)
    private String tenDichVu;

    @ManyToOne
    @JoinColumn(name = "id_chuyen_khoa")
    private ChuyenKhoaEntity chuyenKhoa;

    @Column(name = "mo_ta", columnDefinition = "text")
    private String moTa;

    @Column(name = "gia", precision = 12, scale = 2)
    private BigDecimal gia;

    @Column(name = "thoi_gian_du_kien")
    private Integer thoiGianDuKien;

    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;
} 