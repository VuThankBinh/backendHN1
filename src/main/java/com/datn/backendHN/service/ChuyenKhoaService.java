package com.datn.backendHN.service;

import com.datn.backendHN.dto.ChuyenKhoaDTO;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.KhoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChuyenKhoaService {
    private final ChuyenKhoaRepository chuyenKhoaRepository;
    private final KhoaRepository khoaRepository;

    public List<ChuyenKhoaDTO> getAllChuyenKhoa() {
        return chuyenKhoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ChuyenKhoaDTO> getChuyenKhoaByKhoaId(Integer khoaId) {
        return chuyenKhoaRepository.findByKhoaIdKhoa(khoaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ChuyenKhoaDTO getChuyenKhoaById(Integer id) {
        return chuyenKhoaRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));
    }

    @Transactional
    public ChuyenKhoaDTO createChuyenKhoa(ChuyenKhoaDTO chuyenKhoaDTO) {
        KhoaEntity khoa = khoaRepository.findById(chuyenKhoaDTO.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        if (chuyenKhoaRepository.existsByTenChuyenKhoaAndKhoaIdKhoa(
                chuyenKhoaDTO.getTenChuyenKhoa(), chuyenKhoaDTO.getIdKhoa())) {
            throw new RuntimeException("Tên chuyên khoa đã tồn tại trong khoa này");
        }

        ChuyenKhoaEntity chuyenKhoa = ChuyenKhoaEntity.builder()
                .khoa(khoa)
                .tenChuyenKhoa(chuyenKhoaDTO.getTenChuyenKhoa())
                .moTa(chuyenKhoaDTO.getMoTa())
                .daKichHoat(chuyenKhoaDTO.getDaKichHoat())
                .build();

        return convertToDTO(chuyenKhoaRepository.save(chuyenKhoa));
    }

    @Transactional
    public ChuyenKhoaDTO updateChuyenKhoa(Integer id, ChuyenKhoaDTO chuyenKhoaDTO) {
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));

        KhoaEntity khoa = khoaRepository.findById(chuyenKhoaDTO.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        if (!chuyenKhoa.getTenChuyenKhoa().equals(chuyenKhoaDTO.getTenChuyenKhoa()) &&
                chuyenKhoaRepository.existsByTenChuyenKhoaAndKhoaIdKhoa(
                        chuyenKhoaDTO.getTenChuyenKhoa(), chuyenKhoaDTO.getIdKhoa())) {
            throw new RuntimeException("Tên chuyên khoa đã tồn tại trong khoa này");
        }

        chuyenKhoa.setKhoa(khoa);
        chuyenKhoa.setTenChuyenKhoa(chuyenKhoaDTO.getTenChuyenKhoa());
        chuyenKhoa.setMoTa(chuyenKhoaDTO.getMoTa());
        chuyenKhoa.setDaKichHoat(chuyenKhoaDTO.getDaKichHoat());

        return convertToDTO(chuyenKhoaRepository.save(chuyenKhoa));
    }

    @Transactional
    public void deleteChuyenKhoa(Integer id) {
        if (!chuyenKhoaRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy chuyên khoa");
        }
        chuyenKhoaRepository.deleteById(id);
    }

    private ChuyenKhoaDTO convertToDTO(ChuyenKhoaEntity chuyenKhoa) {
        return ChuyenKhoaDTO.builder()
                .idChuyenKhoa(chuyenKhoa.getIdChuyenKhoa())
                .idKhoa(chuyenKhoa.getKhoa().getIdKhoa())
                .tenChuyenKhoa(chuyenKhoa.getTenChuyenKhoa())
                .moTa(chuyenKhoa.getMoTa())
                .daKichHoat(chuyenKhoa.getDaKichHoat())
                .build();
    }
} 