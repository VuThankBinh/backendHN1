package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Integer id;
    private String email;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private LocalDate ngaySinh;
    private String gioiTinh;
    private String cccd;
    private String vaiTro;
    private Boolean daKichHoat;
    private LocalDate ngayTao;
    private LocalDate ngayCapNhat;
} 