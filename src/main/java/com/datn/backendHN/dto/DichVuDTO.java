package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDTO {
    private Integer id;
    private String tenDichVu;
    private String moTa;
    private String donViTinh;
    private Double donGia;
    private String loaiDichVu;
    private String trangThai;
} 