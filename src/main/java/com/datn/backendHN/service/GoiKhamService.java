package com.datn.backendHN.service;

import com.datn.backendHN.entity.GoiKham;
import com.datn.backendHN.repository.GoiKhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoiKhamService {
    @Autowired
    private GoiKhamRepository goiKhamRepository;

    public List<GoiKham> getAllGoiKham() {
        return goiKhamRepository.findAll();
    }

    public Optional<GoiKham> getGoiKhamById(Integer id) {
        return goiKhamRepository.findById(id);
    }

    public GoiKham createGoiKham(GoiKham goiKham) {
        return goiKhamRepository.save(goiKham);
    }

    public GoiKham updateGoiKham(Integer id, GoiKham goiKhamDetails) {
        GoiKham goiKham = goiKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy gói khám với id: " + id));

        goiKham.setTenGoi(goiKhamDetails.getTenGoi());
        goiKham.setMoTa(goiKhamDetails.getMoTa());
        goiKham.setGia(goiKhamDetails.getGia());
        goiKham.setThoiGianThucHien(goiKhamDetails.getThoiGianThucHien());
        goiKham.setTrangThai(goiKhamDetails.getTrangThai());

        return goiKhamRepository.save(goiKham);
    }

    public void deleteGoiKham(Integer id) {
        GoiKham goiKham = goiKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy gói khám với id: " + id));
        goiKhamRepository.delete(goiKham);
    }
} 