package com.datn.backendHN.dto;

import lombok.Data;

@Data
public class NhanVienDTO {
    private Integer id;
    private Integer chucVuID;
    private ThanhVienDTO thanhVien;
}