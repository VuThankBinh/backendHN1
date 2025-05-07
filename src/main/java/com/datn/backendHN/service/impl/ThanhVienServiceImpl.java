package com.datn.backendHN.service.impl;

import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.repository.ThanhVienRepository;
import com.datn.backendHN.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThanhVienServiceImpl implements ThanhVienService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public ThanhVien findByEmail(String email) {
        return thanhVienRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với email: " + email));
    }

    @Override
    public Optional<ThanhVien> findById(Integer id) {
        return thanhVienRepository.findById(id);
    }

    @Override
    public ThanhVien save(ThanhVien thanhVien) {
        return thanhVienRepository.save(thanhVien);
    }
} 