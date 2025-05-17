package com.datn.backendHN.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeTanResponse {
    private Integer idLeTan;
    private Integer idNguoiDung;
    private String email;
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String ngaySinh;
    private String gioiTinh;
    private String cccd;
    private String caLamViec;
    private Boolean daKichHoat;
} 