package com.datn.backendHN.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhongDTO {
    private Integer idPhong;

    @NotNull(message = "ID khoa không được để trống")
    private Integer idKhoa;

    @NotBlank(message = "Số phòng không được để trống")
    @Size(max = 20, message = "Số phòng không được vượt quá 20 ký tự")
    private String soPhong;

    private Integer tang;

    @Size(max = 20, message = "Trạng thái không được vượt quá 20 ký tự")
    private String trangThai;
} 