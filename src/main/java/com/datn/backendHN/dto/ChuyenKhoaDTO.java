package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenKhoaDTO {
    private Integer idChuyenKhoa;
    private Integer idKhoa;
    private String tenChuyenKhoa;
    private String moTa;
    private Boolean daKichHoat;
} 