package com.datn.backendHN.dto;

import lombok.Data;

@Data
public class ChiTietDonThuocDTO {
    private Integer id;
    private Integer donThuocID;
    private Integer thuocID;
    private Integer soLuong;
    private String huongDan;
} 