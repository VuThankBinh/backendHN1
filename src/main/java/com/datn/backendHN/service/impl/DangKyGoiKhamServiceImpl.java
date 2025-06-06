package com.datn.backendHN.service.impl;

import com.datn.backendHN.entity.DangKyGoiKham;
import com.datn.backendHN.enums.TrangThaiDangKy;
import com.datn.backendHN.repository.DangKyGoiKhamRepository;
import com.datn.backendHN.service.DangKyGoiKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DangKyGoiKhamServiceImpl implements DangKyGoiKhamService {

    @Autowired
    private DangKyGoiKhamRepository dangKyGoiKhamRepository;

    @Override
    public DangKyGoiKham createDangKyGoiKham(DangKyGoiKham dangKyGoiKham) {
        // Kiểm tra xem đã có đăng ký nào cho gói khám này vào giờ và ngày này chưa
        boolean isExisted = dangKyGoiKhamRepository.existsByMaGoiAndNgayThucHienAndGioKhamAndTrangThaiNot(
                dangKyGoiKham.getMaGoi(),
                dangKyGoiKham.getNgayThucHien(),
                dangKyGoiKham.getGioKham(),
                TrangThaiDangKy.HUY // Không tính các đăng ký đã hủy
        );

        if (isExisted) {
            throw new RuntimeException("Gói khám này đã được đăng ký vào giờ " + dangKyGoiKham.getGioKham() + 
                    " ngày " + dangKyGoiKham.getNgayThucHien());
        }

        return dangKyGoiKhamRepository.save(dangKyGoiKham);
    }

    @Override
    public DangKyGoiKham updateDangKyGoiKham(Integer maDangKy, DangKyGoiKham dangKyGoiKham) {
        DangKyGoiKham existingDangKy = dangKyGoiKhamRepository.findById(maDangKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký gói khám với mã: " + maDangKy));
        
        existingDangKy.setMaHoSo(dangKyGoiKham.getMaHoSo());
        existingDangKy.setMaGoi(dangKyGoiKham.getMaGoi());
        existingDangKy.setNgayDangKy(dangKyGoiKham.getNgayDangKy());
        existingDangKy.setNgayThucHien(dangKyGoiKham.getNgayThucHien());
        existingDangKy.setTrangThai(dangKyGoiKham.getTrangThai());
        existingDangKy.setGiaTien(dangKyGoiKham.getGiaTien());
        existingDangKy.setGioKham(dangKyGoiKham.getGioKham());
        existingDangKy.setUserId(dangKyGoiKham.getUserId());

        return dangKyGoiKhamRepository.save(existingDangKy);
    }

    @Override
    public DangKyGoiKham updateTrangThai(Integer maDangKy, TrangThaiDangKy trangThai) {
        DangKyGoiKham existingDangKy = dangKyGoiKhamRepository.findById(maDangKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký gói khám với mã: " + maDangKy));
        
        existingDangKy.setTrangThai(trangThai);
        return dangKyGoiKhamRepository.save(existingDangKy);
    }

    @Override
    public void deleteDangKyGoiKham(Integer maDangKy) {
        dangKyGoiKhamRepository.deleteById(maDangKy);
    }

    @Override
    public DangKyGoiKham getDangKyGoiKhamById(Integer maDangKy) {
        return dangKyGoiKhamRepository.findById(maDangKy)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký gói khám với mã: " + maDangKy));
    }

    @Override
    public List<DangKyGoiKham> getDangKyGoiKhamByUserId(Integer userId) {
        return dangKyGoiKhamRepository.findByUserId(userId);
    }

    @Override
    public List<DangKyGoiKham> getDangKyGoiKhamByTrangThai(TrangThaiDangKy trangThai) {
        return dangKyGoiKhamRepository.findByTrangThai(trangThai);
    }

    @Override
    public List<DangKyGoiKham> getDangKyGoiKhamByUserIdAndTrangThai(Integer userId, TrangThaiDangKy trangThai) {
        return dangKyGoiKhamRepository.findByUserIdAndTrangThai(userId, trangThai);
    }

    @Override
    public List<DangKyGoiKham> getAllDangKyGoiKham() {
        return dangKyGoiKhamRepository.findAll();
    }
} 