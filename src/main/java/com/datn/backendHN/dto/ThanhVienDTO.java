package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhVienDTO {
    private Integer id;
    private String soDienThoai;
    private String hoTen;
    private String diaChi;
    private String email;
    private String tenDangNhap;
    private String matKhau;
} 