package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phong_kham")
public class PhongKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_phong")
    private String tenPhong;
} 