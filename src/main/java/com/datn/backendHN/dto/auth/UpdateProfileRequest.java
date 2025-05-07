package com.datn.backendHN.dto.auth;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String diaChi;
} 