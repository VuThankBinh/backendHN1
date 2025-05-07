package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "goi_kham")
@EqualsAndHashCode(callSuper = false)
public class GoiKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_goi")
    private String tenGoi;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia")
    private Double gia;
} 