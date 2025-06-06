package com.datn.backendHN.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BacSiResponse {
    private Integer idBacSi;
    private Integer idNguoiDung;
    private String email;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String ngaySinh;
    private String gioiTinh;
    private String cccd;
    private Integer idChuyenKhoa;
    private String tenChuyenKhoa;
    private Integer idKhoa;
    private String tenKhoa;
    private String chuyenMon;
    private String soGiayPhep;
    private Integer soNamKinhNghiem;
    private Boolean dangLamViec;
    private Boolean daKichHoat;
    private String anhDaiDien;
} 