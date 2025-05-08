package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.GoiKhamDTO;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.GoiKham;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.GoiKhamRepository;
import com.datn.backendHN.service.GoiKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoiKhamServiceImpl implements GoiKhamService {

    @Autowired
    private GoiKhamRepository goiKhamRepository;

    @Autowired
    private ChuyenKhoaRepository chuyenKhoaRepository;

    @Override
    @Transactional
    public GoiKham createGoiKham(GoiKhamDTO goiKhamDTO) {
        GoiKham goiKham = new GoiKham();
        goiKham.setTenGoiKham(goiKhamDTO.getTenGoiKham());
        goiKham.setMoTa(goiKhamDTO.getMoTa());
        goiKham.setGiaTien(goiKhamDTO.getGiaTien());
        goiKham.setTrangThai(goiKhamDTO.getTrangThai());

        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(goiKhamDTO.getChuyenKhoaId())
                .orElseThrow(() -> new RuntimeException("Chuyên khoa không tồn tại"));
        goiKham.setChuyenKhoa(chuyenKhoa);

        return goiKhamRepository.save(goiKham);
    }

    @Override
    @Transactional
    public GoiKham updateGoiKham(Integer id, GoiKhamDTO goiKhamDTO) {
        GoiKham goiKham = goiKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gói khám không tồn tại"));

        goiKham.setTenGoiKham(goiKhamDTO.getTenGoiKham());
        goiKham.setMoTa(goiKhamDTO.getMoTa());
        goiKham.setGiaTien(goiKhamDTO.getGiaTien());
        goiKham.setTrangThai(goiKhamDTO.getTrangThai());

        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(goiKhamDTO.getChuyenKhoaId())
                .orElseThrow(() -> new RuntimeException("Chuyên khoa không tồn tại"));
        goiKham.setChuyenKhoa(chuyenKhoa);

        return goiKhamRepository.save(goiKham);
    }

    @Override
    @Transactional
    public void deleteGoiKham(Integer id) {
        GoiKham goiKham = goiKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gói khám không tồn tại"));
        goiKhamRepository.delete(goiKham);
    }

    @Override
    public GoiKham getGoiKhamById(Integer id) {
        return goiKhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gói khám không tồn tại"));
    }

    @Override
    public List<GoiKham> getAllGoiKham() {
        return goiKhamRepository.findAll();
    }

    @Override
    public List<GoiKham> getGoiKhamByChuyenKhoa(Integer chuyenKhoaId) {
        return goiKhamRepository.findByChuyenKhoaId(chuyenKhoaId);
    }

    @Override
    public List<GoiKham> getGoiKhamByTrangThai(String trangThai) {
        return goiKhamRepository.findByTrangThai(trangThai);
    }
} 