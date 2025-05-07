package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "benh_nhan")
@PrimaryKeyJoinColumn(name = "id")
public class BenhNhan extends ThanhVien {
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "dan_toc")
    private String danToc;

} 