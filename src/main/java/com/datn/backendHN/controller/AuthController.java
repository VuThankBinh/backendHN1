package com.datn.backendHN.controller;

import com.datn.backendHN.dto.*;
import com.datn.backendHN.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        authService.changePassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @Valid @RequestBody LoginRequest request
    ) {
        authService.resetPassword(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/kich-hoat/{email}")
    public ResponseEntity<Void> kichHoatTaiKhoan(
            @PathVariable String email
    ) {
        authService.kichHoatTaiKhoan(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> layThongTinNguoiDung() {
        return ResponseEntity.ok(authService.layThongTinNguoiDung());
    }
} 