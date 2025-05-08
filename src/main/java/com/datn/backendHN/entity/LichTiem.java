package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lich_tiem")
public class LichTiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bac_si_id", nullable = false)
    private Integer bacSiId;

    @Column(name = "loai_vaccine", nullable = false)
    private String loaiVaccine;

    @Column(name = "ngay_tiem", nullable = false)
    private LocalDate ngayTiem;

    @Column(name = "gio_tiem", nullable = false)
    private LocalTime gioTiem;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai; // CHỜ_TIÊM, ĐÃ_TIÊM, ĐÃ_HỦY

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "bac_si_id", insertable = false, updatable = false)
    private BacSi bacSi;
} 