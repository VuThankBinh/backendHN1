package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDTO {
    private Integer idDichVu;
    private String tenDichVu;
    private Integer idChuyenKhoa;
    private String moTa;
    private BigDecimal gia;
    private Integer thoiGianDuKien;
    private Boolean daKichHoat;
} 