package com.datn.backendHN.service;

import com.datn.backendHN.dto.GoiKhamDTO;
import com.datn.backendHN.entity.GoiKham;
import java.util.List;

public interface GoiKhamService {
    GoiKham createGoiKham(GoiKhamDTO goiKhamDTO);
    GoiKham updateGoiKham(Integer id, GoiKhamDTO goiKhamDTO);
    void deleteGoiKham(Integer id);
    GoiKham getGoiKhamById(Integer id);
    List<GoiKham> getAllGoiKham();
    List<GoiKham> getGoiKhamByChuyenKhoa(Integer chuyenKhoaId);
    List<GoiKham> getGoiKhamByTrangThai(String trangThai);
} 