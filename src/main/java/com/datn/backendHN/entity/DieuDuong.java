package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "dieu_duong")
@EqualsAndHashCode(callSuper = false)
public class DieuDuong {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private NhanVien nhanVien;
} 