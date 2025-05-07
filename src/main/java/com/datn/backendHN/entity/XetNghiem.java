package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xet_nghiem")
@EqualsAndHashCode(callSuper = false)
public class XetNghiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ho_so_id")
    private HoSoBenhAn hoSoBenhAn;

    @Column(name = "loai_xet_nghiem")
    private String loaiXetNghiem;

    @Column(name = "ket_qua")
    private String ketQua;

    @Column(name = "ngay_thuc_hien")
    private LocalDateTime ngayThucHien;
} 