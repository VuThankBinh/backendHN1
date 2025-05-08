package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeTanDTO {
    private Integer id;
    private Integer chucVuId;
    private ThanhVienDTO thanhVien;
} 