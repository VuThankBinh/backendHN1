package com.datn.backendHN.dto.auth;

import com.datn.backendHN.entity.ThanhVien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private ThanhVien user;
} 