package com.datn.backendHN.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBacSiRequest {
    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được vượt quá 100 ký tự")
    private String hoTen;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{10,15}$", message = "Số điện thoại không hợp lệ")
    private String soDienThoai;

    private String diaChi;
    private String ngaySinh;
    private String gioiTinh;
    private String cccd;

    @NotNull(message = "Chuyên khoa không được để trống")
    private Integer idChuyenKhoa;

    @NotNull(message = "Khoa không được để trống")
    private Integer idKhoa;

    private String chuyenMon;
    
    @NotBlank(message = "Số giấy phép không được để trống")
    private String soGiayPhep;
    
    private Integer soNamKinhNghiem;
    
    private Boolean dangLamViec;

    private String anhDaiDien;
} 