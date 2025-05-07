package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.auth.*;
import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.repository.ThanhVienRepository;
import com.datn.backendHN.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ThanhVien login(LoginRequest loginRequest) {
        // Lấy thông tin người dùng từ repository
        return thanhVienRepository.findByTenDangNhapOrEmail(loginRequest.getUsernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Tên đăng nhập hoặc email không tồn tại"));
    }

    @Override
    public ThanhVien getUserById(Integer userId) {
        return thanhVienRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }

    @Override
    public ThanhVien register(RegisterRequest registerRequest) {
        if (thanhVienRepository.existsByTenDangNhap(registerRequest.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (thanhVienRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        if (thanhVienRepository.existsBySoDienThoai(registerRequest.getSoDienThoai())) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }

        ThanhVien thanhVien = new ThanhVien();
        thanhVien.setHoTen(registerRequest.getHoTen());
        thanhVien.setSoDienThoai(registerRequest.getSoDienThoai());
        thanhVien.setEmail(registerRequest.getEmail());
        thanhVien.setDiaChi(registerRequest.getDiaChi());
        thanhVien.setTenDangNhap(registerRequest.getTenDangNhap());
        thanhVien.setMatKhau(passwordEncoder.encode(registerRequest.getMatKhau()));
        thanhVien.setVaiTro(registerRequest.getVaiTro());

        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public void changePassword(Integer userId, ChangePasswordRequest request) {
        ThanhVien thanhVien = thanhVienRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (!passwordEncoder.matches(request.getMatKhauCu(), thanhVien.getMatKhau())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        thanhVien.setMatKhau(passwordEncoder.encode(request.getMatKhauMoi()));
        thanhVienRepository.save(thanhVien);
    }

    @Override
    public ThanhVien updateProfile(Integer userId, UpdateProfileRequest request) {
        ThanhVien thanhVien = thanhVienRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        // Check if new email is already taken by another user
        if (!thanhVien.getEmail().equals(request.getEmail()) && 
            thanhVienRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Check if new phone number is already taken by another user
        if (!thanhVien.getSoDienThoai().equals(request.getSoDienThoai()) && 
            thanhVienRepository.existsBySoDienThoai(request.getSoDienThoai())) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }

        thanhVien.setHoTen(request.getHoTen());
        thanhVien.setSoDienThoai(request.getSoDienThoai());
        thanhVien.setEmail(request.getEmail());
        thanhVien.setDiaChi(request.getDiaChi());

        return thanhVienRepository.save(thanhVien);
    }
} 