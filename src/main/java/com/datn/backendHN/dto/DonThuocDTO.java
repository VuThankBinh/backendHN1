package com.datn.backendHN.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DonThuocDTO {
    private Integer id;
    private Integer hoSoID;
    private LocalDate ngayKe;
} 