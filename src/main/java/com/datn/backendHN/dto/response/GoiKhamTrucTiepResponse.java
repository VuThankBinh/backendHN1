package com.datn.backendHN.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoiKhamTrucTiepResponse {
    private Integer idGoiKham;
    private String tenGoiKham;
    private String moTa;
    private BigDecimal giaTien;
    private Integer idChuyenKhoa;
    private String tenChuyenKhoa;
    private Boolean trangThai;
    private Integer thoiGianKham;
} 