package com.datn.backendHN.service;

import com.datn.backendHN.dto.auth.*;
import com.datn.backendHN.entity.ThanhVien;

public interface AuthService {
    ThanhVien login(LoginRequest loginRequest);
    ThanhVien register(RegisterRequest registerRequest);
    void changePassword(Integer userId, ChangePasswordRequest request);
    ThanhVien updateProfile(Integer userId, UpdateProfileRequest request);
    ThanhVien getUserById(Integer userId);
} 