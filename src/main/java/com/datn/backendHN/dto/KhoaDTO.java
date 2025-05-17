package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KhoaDTO {
    private Integer idKhoa;
    private String tenKhoa;
    private String moTa;
    private Boolean daKichHoat;
} 