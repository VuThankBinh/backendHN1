package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichHenKhamDTO {
    private Integer id;
    private LocalDateTime thoiGian;
    private Integer benhNhanId;
    private Integer bacSiId;
    private Integer phongKhamId;
    private String trangThai;
} 