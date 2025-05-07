package com.datn.backendHN.service;

import com.datn.backendHN.entity.ThanhVien;
import java.util.Optional;

public interface ThanhVienService {
    ThanhVien findByEmail(String email);
    Optional<ThanhVien> findById(Integer id);
    ThanhVien save(ThanhVien thanhVien);
} 