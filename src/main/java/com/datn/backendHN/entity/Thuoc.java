package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "thuoc")
@EqualsAndHashCode(callSuper = false)
public class Thuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_thuoc")
    private String tenThuoc;

    @Column(name = "don_vi")
    private String donVi;

    @Column(name = "lieu_luong")
    private String lieuLuong;

    @Column(name = "tac_dung")
    private String tacDung;
} 