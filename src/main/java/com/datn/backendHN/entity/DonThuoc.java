package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "don_thuoc")
@EqualsAndHashCode(callSuper = false)
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ho_so_id")
    private HoSoBenhAn hoSoBenhAn;

    @Column(name = "ngay_ke")
    private LocalDate ngayKe;
} 