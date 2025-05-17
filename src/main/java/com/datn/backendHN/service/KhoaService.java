package com.datn.backendHN.service;

import com.datn.backendHN.dto.KhoaDTO;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.repository.KhoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KhoaService {
    private final KhoaRepository khoaRepository;

    public List<KhoaDTO> getAllKhoa() {
        return khoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public KhoaDTO getKhoaById(Integer id) {
        return khoaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));
    }

    @Transactional
    public KhoaDTO createKhoa(KhoaDTO khoaDTO) {
        if (khoaRepository.existsByTenKhoa(khoaDTO.getTenKhoa())) {
            throw new RuntimeException("Tên khoa đã tồn tại");
        }

        KhoaEntity khoa = KhoaEntity.builder()
                .tenKhoa(khoaDTO.getTenKhoa())
                .moTa(khoaDTO.getMoTa())
                .daKichHoat(khoaDTO.getDaKichHoat())
                .build();

        return convertToDTO(khoaRepository.save(khoa));
    }

    @Transactional
    public KhoaDTO updateKhoa(Integer id, KhoaDTO khoaDTO) {
        KhoaEntity khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        if (!khoa.getTenKhoa().equals(khoaDTO.getTenKhoa()) &&
                khoaRepository.existsByTenKhoa(khoaDTO.getTenKhoa())) {
            throw new RuntimeException("Tên khoa đã tồn tại");
        }

        khoa.setTenKhoa(khoaDTO.getTenKhoa());
        khoa.setMoTa(khoaDTO.getMoTa());
        khoa.setDaKichHoat(khoaDTO.getDaKichHoat());

        return convertToDTO(khoaRepository.save(khoa));
    }

    @Transactional
    public void deleteKhoa(Integer id) {
        if (!khoaRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy khoa");
        }
        khoaRepository.deleteById(id);
    }

    private KhoaDTO convertToDTO(KhoaEntity khoa) {
        return KhoaDTO.builder()
                .idKhoa(khoa.getIdKhoa())
                .tenKhoa(khoa.getTenKhoa())
                .moTa(khoa.getMoTa())
                .daKichHoat(khoa.getDaKichHoat())
                .build();
    }
} 