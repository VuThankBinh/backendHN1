package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bao_hiem_y_te")
@EqualsAndHashCode(callSuper = false)
public class BaoHiemYTe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "benh_nhan_id")
    private BenhNhan benhNhan;

    @Column(name = "ma_bao_hieu")
    private String maBaoHiem;

    @Column(name = "ngay_cap")
    private LocalDate ngayCap;

    @Column(name = "ngay_het_han")
    private LocalDate ngayHetHan;

    @Column(name = "noi_cap")
    private String noiCap;

    @Column(name = "muc_huong")
    private String mucHuong;
} 