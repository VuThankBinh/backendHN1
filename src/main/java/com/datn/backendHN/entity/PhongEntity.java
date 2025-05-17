package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phong")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phong")
    private Integer idPhong;

    @ManyToOne
    @JoinColumn(name = "id_khoa", nullable = false)
    private KhoaEntity khoa;

    @Column(name = "so_phong", nullable = false, length = 20)
    private String soPhong;

    @Column(name = "tang")
    private Integer tang;

    @Column(name = "trang_thai", length = 20)
    private String trangThai;
} 