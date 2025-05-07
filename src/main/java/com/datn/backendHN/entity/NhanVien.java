package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhan_vien")
@PrimaryKeyJoinColumn(name = "id")
public class NhanVien extends ThanhVien {
    @ManyToOne
    @JoinColumn(name = "chuc_vu_id", nullable = false)
    private ChucVu chucVu;
} 