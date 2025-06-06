package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bac_si")
public class BacSiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bac_si")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id_nguoi_dung")
    private NguoiDungEntity nguoiDung;

    @ManyToOne
    @JoinColumn(name = "id_chuyen_khoa")
    private ChuyenKhoaEntity chuyenKhoa;

    @ManyToOne
    @JoinColumn(name = "id_khoa")
    private KhoaEntity khoa;

    @Column(name = "chuyen_mon")
    private String chuyenMon;

    @Column(name = "so_giay_phep", unique = true)
    private String soGiayPhep;

    @Column(name = "so_nam_kinh_nghiem")
    private Integer soNamKinhNghiem;

    @Column(name = "dang_lam_viec")
    private Boolean dangLamViec;

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;
} 