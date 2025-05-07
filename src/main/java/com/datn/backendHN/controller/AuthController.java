package com.datn.backendHN.controller;

import com.datn.backendHN.dto.auth.*;
import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.service.AuthService;
import com.datn.backendHN.security.JwtUtils;
import com.datn.backendHN.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(),
                    loginRequest.getMatKhau()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Lấy thông tin người dùng từ UserDetails
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            
            // Lấy thông tin đầy đủ của người dùng từ service
            ThanhVien user = authService.getUserById(userDetails.getId());
            
            // Tạo JWT token
            String jwt = jwtUtils.generateJwtToken(authentication);
            
            // Trả về token và thông tin người dùng
            return ResponseEntity.ok(new LoginResponse(jwt, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đăng nhập thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ThanhVien> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<Void> changePassword(
            @PathVariable Integer userId,
            @RequestBody ChangePasswordRequest request) {
        authService.changePassword(userId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<ThanhVien> updateProfile(
            @PathVariable Integer userId,
            @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(authService.updateProfile(userId, request));
    }
} 