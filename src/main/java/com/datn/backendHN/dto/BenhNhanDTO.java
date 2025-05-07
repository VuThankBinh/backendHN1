package com.datn.backendHN.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BenhNhanDTO extends ThanhVienDTO {
    private LocalDate ngaySinh;
    private String danToc;
} 