package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chuyen_khoa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenKhoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chuyen_khoa")
    private Integer idChuyenKhoa;

    @ManyToOne
    @JoinColumn(name = "id_khoa", nullable = false)
    private KhoaEntity khoa;

    @Column(name = "ten_chuyen_khoa", nullable = false, length = 100)
    private String tenChuyenKhoa;

    @Column(name = "mo_ta", columnDefinition = "text")
    private String moTa;

    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;
} 