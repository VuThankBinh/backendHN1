package com.datn.backendHN.service;

import com.datn.backendHN.dto.request.CreateGoiKhamTrucTiepRequest;
import com.datn.backendHN.dto.response.GoiKhamTrucTiepResponse;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.GoiKhamTrucTiepEntity;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.GoiKhamTrucTiepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoiKhamTrucTiepService {
    private final GoiKhamTrucTiepRepository goiKhamTrucTiepRepository;
    private final ChuyenKhoaRepository chuyenKhoaRepository;

    public List<GoiKhamTrucTiepResponse> getAllGoiKham() {
        return goiKhamTrucTiepRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public GoiKhamTrucTiepResponse getGoiKhamById(Integer id) {
        return goiKhamTrucTiepRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy gói khám"));
    }

    @Transactional
    public GoiKhamTrucTiepResponse createGoiKham(CreateGoiKhamTrucTiepRequest request) {
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(request.getIdChuyenKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));

        GoiKhamTrucTiepEntity goiKham = GoiKhamTrucTiepEntity.builder()
                .tenGoiKham(request.getTenGoiKham())
                .moTa(request.getMoTa())
                .giaTien(request.getGiaTien())
                .chuyenKhoa(chuyenKhoa)
                .trangThai(request.getTrangThai())
                .thoiGianKham(request.getThoiGianKham())
                .build();

        return convertToResponse(goiKhamTrucTiepRepository.save(goiKham));
    }

    public List<GoiKhamTrucTiepResponse> searchGoiKham(String tenGoiKham, Integer idChuyenKhoa, Boolean trangThai) {
        return goiKhamTrucTiepRepository.searchGoiKham(tenGoiKham, idChuyenKhoa, trangThai).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<GoiKhamTrucTiepResponse> getGoiKhamByChuyenKhoa(Integer idChuyenKhoa) {
        return goiKhamTrucTiepRepository.findByChuyenKhoaIdChuyenKhoa(idChuyenKhoa).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private GoiKhamTrucTiepResponse convertToResponse(GoiKhamTrucTiepEntity goiKham) {
        return GoiKhamTrucTiepResponse.builder()
                .idGoiKham(goiKham.getId())
                .tenGoiKham(goiKham.getTenGoiKham())
                .moTa(goiKham.getMoTa())
                .giaTien(goiKham.getGiaTien())
                .idChuyenKhoa(goiKham.getChuyenKhoa().getIdChuyenKhoa())
                .tenChuyenKhoa(goiKham.getChuyenKhoa().getTenChuyenKhoa())
                .trangThai(goiKham.getTrangThai())
                .thoiGianKham(goiKham.getThoiGianKham())
                .build();
    }
} 