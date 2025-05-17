package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "khoa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhoaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khoa")
    private Integer idKhoa;

    @Column(name = "ten_khoa", nullable = false, length = 100)
    private String tenKhoa;

    @Column(name = "mo_ta", columnDefinition = "text")
    private String moTa;

    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;

    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL)
    private List<ChuyenKhoaEntity> chuyenKhoas;
} 