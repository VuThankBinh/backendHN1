package com.datn.backendHN.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhongResponse {
    private Integer idPhong;
    private Integer idKhoa;
    private String tenKhoa;
    private String soPhong;
    private Integer tang;
    private String trangThai;
} 