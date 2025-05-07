package com.datn.backendHN.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
} 