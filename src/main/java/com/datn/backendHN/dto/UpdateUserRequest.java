package com.datn.backendHN.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull(message = "ID người dùng không được để trống")
    private Integer userId;

    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại phải có 10 chữ số")
    private String soDienThoai;
    
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;
    
    @NotBlank(message = "Ngày sinh không được để trống")
    private String ngaySinh;
    
    @NotBlank(message = "Giới tính không được để trống")
    private String gioiTinh;
} 