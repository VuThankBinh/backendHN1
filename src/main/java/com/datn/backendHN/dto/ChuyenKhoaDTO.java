package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenKhoaDTO {
    private Integer id;
    private String tenChuyenKhoa;
    private String moTa;
    private Integer khoaId;
} 