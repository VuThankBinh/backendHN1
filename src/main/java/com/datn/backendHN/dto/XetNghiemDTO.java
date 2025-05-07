package com.datn.backendHN.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class XetNghiemDTO {
    private Integer id;
    private Integer hoSoID;
    private String loaiXetNghiem;
    private String ketQua;
    private LocalDateTime thoiGian;
} 