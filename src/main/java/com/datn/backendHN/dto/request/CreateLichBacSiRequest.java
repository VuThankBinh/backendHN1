package com.datn.backendHN.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLichBacSiRequest {
    @NotNull(message = "ID bác sĩ không được để trống")
    private Integer idBacSi;

    @NotBlank(message = "Thứ không được để trống")
    @Pattern(regexp = "^(THU_2|THU_3|THU_4|THU_5|THU_6|THU_7|CN)$", message = "Thứ không hợp lệ")
    private String thu;

    @NotBlank(message = "Giờ bắt đầu không được để trống")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ bắt đầu không hợp lệ")
    private String gioBatDau;

    @NotBlank(message = "Giờ kết thúc không được để trống")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ kết thúc không hợp lệ")
    private String gioKetThuc;

    private Boolean dangKhaDung;

    private Boolean laLichLap;

    private String cacNgayLap;

    private String ngayBatDau;

    private String ngayKetThuc;
} 