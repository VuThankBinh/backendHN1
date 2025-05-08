package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LichLamViecDTO {
    private Integer id;
    private Integer bacSiId;
    private LocalDate ngay;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
} 