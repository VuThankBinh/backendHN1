package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "chi_tiet_don_thuoc")
@EqualsAndHashCode(callSuper = false)
public class ChiTietDonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "don_thuoc_id")
    private DonThuoc donThuoc;

    @ManyToOne
    @JoinColumn(name = "thuoc_id")
    private Thuoc thuoc;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "huong_dan")
    private String huongDan;
} 