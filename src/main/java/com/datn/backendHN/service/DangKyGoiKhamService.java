package com.datn.backendHN.service;

import com.datn.backendHN.entity.DangKyGoiKham;
import com.datn.backendHN.enums.TrangThaiDangKy;
import java.util.List;

public interface DangKyGoiKhamService {
    DangKyGoiKham createDangKyGoiKham(DangKyGoiKham dangKyGoiKham);
    DangKyGoiKham updateDangKyGoiKham(Integer maDangKy, DangKyGoiKham dangKyGoiKham);
    void deleteDangKyGoiKham(Integer maDangKy);
    DangKyGoiKham getDangKyGoiKhamById(Integer maDangKy);
    List<DangKyGoiKham> getDangKyGoiKhamByUserId(Integer userId);
    List<DangKyGoiKham> getDangKyGoiKhamByTrangThai(TrangThaiDangKy trangThai);
    List<DangKyGoiKham> getDangKyGoiKhamByUserIdAndTrangThai(Integer userId, TrangThaiDangKy trangThai);
    List<DangKyGoiKham> getAllDangKyGoiKham();
    DangKyGoiKham updateTrangThai(Integer maDangKy, TrangThaiDangKy trangThai);
} 