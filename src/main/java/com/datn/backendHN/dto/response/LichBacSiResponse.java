package com.datn.backendHN.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LichBacSiResponse {
    private Integer idLichBacSi;
    private Integer idBacSi;
    private String tenBacSi;
    private String thu;
    private String gioBatDau;
    private String gioKetThuc;
    private Boolean dangKhaDung;
    private Boolean laLichLap;
    private String cacNgayLap;
    private String ngayBatDau;
    private String ngayKetThuc;
} 