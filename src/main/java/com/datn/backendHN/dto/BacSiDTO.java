package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BacSiDTO extends ThanhVienDTO {
    private Integer chucVuId;
    private Integer chuyenKhoaId;
    private Integer phongKhamId;
    private String bangCap;
    private Integer kinhNghiem;
} 