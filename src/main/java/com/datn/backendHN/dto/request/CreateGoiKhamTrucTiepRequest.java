package com.datn.backendHN.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoiKhamTrucTiepRequest {
    @NotBlank(message = "Tên gói khám không được để trống")
    private String tenGoiKham;

    private String moTa;

    @NotNull(message = "Giá tiền không được để trống")
    @Positive(message = "Giá tiền phải lớn hơn 0")
    private BigDecimal giaTien;

    @NotNull(message = "Chuyên khoa không được để trống")
    private Integer idChuyenKhoa;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean trangThai;

    @Positive(message = "Thời gian khám phải lớn hơn 0")
    private Integer thoiGianKham;
} 