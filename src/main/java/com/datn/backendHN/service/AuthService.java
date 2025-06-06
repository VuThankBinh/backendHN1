package com.datn.backendHN.service;

import com.datn.backendHN.dto.*;
import com.datn.backendHN.entity.NguoiDungEntity;
import com.datn.backendHN.enums.VaiTro;
import com.datn.backendHN.repository.NguoiDungRepository;
import com.datn.backendHN.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        validateRegisterRequest(request);

        var nguoiDung = NguoiDungEntity.builder()
                .email(request.getEmail())
                .matKhau(passwordEncoder.encode(request.getMatKhau()))
                .hoTen(request.getHoTen())
                .soDienThoai(request.getSoDienThoai())
                .diaChi(request.getDiaChi())
                .ngaySinh(LocalDate.parse(request.getNgaySinh()))
                .gioiTinh(request.getGioiTinh())
                .cccd(request.getCccd())
                .vaiTro(VaiTro.BENH_NHAN.toString())
                .daKichHoat(false)
                .ngayTao(LocalDate.now())
                .ngayCapNhat(LocalDate.now())
                .build();

        nguoiDungRepository.save(nguoiDung);
        return createAuthResponse(nguoiDung);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getMatKhau()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Email hoặc mật khẩu không đúng");
        }

        var nguoiDung = nguoiDungRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        return createAuthResponse(nguoiDung);
    }

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        var nguoiDung = nguoiDungRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (!passwordEncoder.matches(request.getOldPassword(), nguoiDung.getMatKhau())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu mới không khớp");
        }

        nguoiDung.setMatKhau(passwordEncoder.encode(request.getNewPassword()));
        nguoiDung.setNgayCapNhat(LocalDate.now());
        nguoiDungRepository.save(nguoiDung);
    }

    @Transactional
    public void resetPassword(LoginRequest request) {
        var nguoiDung = nguoiDungRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        String newPassword = request.getMatKhau();
        nguoiDung.setMatKhau(passwordEncoder.encode(newPassword));
        nguoiDung.setNgayCapNhat(LocalDate.now());
        nguoiDungRepository.save(nguoiDung);

        // TODO: Gửi email chứa mật khẩu mới
        System.out.println("Mật khẩu mới: " + newPassword);
    }

    @Transactional
    public void kichHoatTaiKhoan(String email) {
        var nguoiDung = nguoiDungRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        if (nguoiDung.getDaKichHoat()) {
            throw new RuntimeException("Tài khoản đã được kích hoạt");
        }

        nguoiDung.setDaKichHoat(true);
        nguoiDung.setNgayCapNhat(LocalDate.now());
        nguoiDungRepository.save(nguoiDung);
    }

    public UserInfoResponse layThongTinNguoiDung() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var nguoiDung = nguoiDungRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        return UserInfoResponse.builder()
                .id(nguoiDung.getIdNguoiDung())
                .email(nguoiDung.getEmail())
                .hoTen(nguoiDung.getHoTen())
                .soDienThoai(nguoiDung.getSoDienThoai())
                .diaChi(nguoiDung.getDiaChi())
                .ngaySinh(nguoiDung.getNgaySinh())
                .gioiTinh(nguoiDung.getGioiTinh())
                .cccd(nguoiDung.getCccd())
                .vaiTro(nguoiDung.getVaiTro())
                .daKichHoat(nguoiDung.getDaKichHoat())
                .ngayTao(nguoiDung.getNgayTao())
                .ngayCapNhat(nguoiDung.getNgayCapNhat())
                .build();
    }

    @Transactional
    public UserInfoResponse capNhatThongTin(UpdateUserRequest request) {
        var nguoiDung = nguoiDungRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        nguoiDung.setHoTen(request.getHoTen());
        nguoiDung.setSoDienThoai(request.getSoDienThoai());
        nguoiDung.setDiaChi(request.getDiaChi());
        nguoiDung.setNgaySinh(LocalDate.parse(request.getNgaySinh()));
        nguoiDung.setGioiTinh(request.getGioiTinh());
        nguoiDung.setNgayCapNhat(LocalDate.now());

        nguoiDungRepository.save(nguoiDung);

        return UserInfoResponse.builder()
                .id(nguoiDung.getIdNguoiDung())
                .email(nguoiDung.getEmail())
                .hoTen(nguoiDung.getHoTen())
                .soDienThoai(nguoiDung.getSoDienThoai())
                .diaChi(nguoiDung.getDiaChi())
                .ngaySinh(nguoiDung.getNgaySinh())
                .gioiTinh(nguoiDung.getGioiTinh())
                .cccd(nguoiDung.getCccd())
                .vaiTro(nguoiDung.getVaiTro())
                .daKichHoat(nguoiDung.getDaKichHoat())
                .ngayTao(nguoiDung.getNgayTao())
                .ngayCapNhat(nguoiDung.getNgayCapNhat())
                .build();
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (nguoiDungRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (nguoiDungRepository.existsByCccd(request.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }
    }

    private AuthResponse createAuthResponse(NguoiDungEntity nguoiDung) {
        var jwtToken = jwtService.generateToken(nguoiDung);
        return AuthResponse.builder()
                .token(jwtToken)
                .email(nguoiDung.getEmail())
                .hoTen(nguoiDung.getHoTen())
                .vaiTro(nguoiDung.getVaiTro())
                .build();
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
} 