package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichTiemDTO {
    private Integer id;
    private Integer bacSiId;
    private String loaiVaccine;
    private LocalDate ngayTiem;
    private LocalTime gioTiem;
    private String trangThai;
    private String ghiChu;
} 