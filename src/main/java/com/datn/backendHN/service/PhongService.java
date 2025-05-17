package com.datn.backendHN.service;

import com.datn.backendHN.dto.PhongDTO;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.entity.PhongEntity;
import com.datn.backendHN.repository.KhoaRepository;
import com.datn.backendHN.repository.PhongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhongService {
    private final PhongRepository phongRepository;
    private final KhoaRepository khoaRepository;

    public List<PhongDTO> getAllPhong() {
        return phongRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PhongDTO> getPhongByKhoaId(Integer khoaId) {
        return phongRepository.findByKhoaIdKhoa(khoaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PhongDTO getPhongById(Integer id) {
        return phongRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));
    }

    @Transactional
    public PhongDTO createPhong(PhongDTO phongDTO) {
        KhoaEntity khoa = khoaRepository.findById(phongDTO.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        if (phongRepository.existsBySoPhongAndKhoaIdKhoa(phongDTO.getSoPhong(), phongDTO.getIdKhoa())) {
            throw new RuntimeException("Số phòng đã tồn tại trong khoa này");
        }

        PhongEntity phong = PhongEntity.builder()
                .khoa(khoa)
                .soPhong(phongDTO.getSoPhong())
                .tang(phongDTO.getTang())
                .trangThai(phongDTO.getTrangThai())
                .build();

        return convertToDTO(phongRepository.save(phong));
    }

    @Transactional
    public PhongDTO updatePhong(Integer id, PhongDTO phongDTO) {
        PhongEntity phong = phongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        KhoaEntity khoa = khoaRepository.findById(phongDTO.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        if (!phong.getSoPhong().equals(phongDTO.getSoPhong()) &&
                phongRepository.existsBySoPhongAndKhoaIdKhoa(phongDTO.getSoPhong(), phongDTO.getIdKhoa())) {
            throw new RuntimeException("Số phòng đã tồn tại trong khoa này");
        }

        phong.setKhoa(khoa);
        phong.setSoPhong(phongDTO.getSoPhong());
        phong.setTang(phongDTO.getTang());
        phong.setTrangThai(phongDTO.getTrangThai());

        return convertToDTO(phongRepository.save(phong));
    }

    @Transactional
    public void deletePhong(Integer id) {
        if (!phongRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy phòng");
        }
        phongRepository.deleteById(id);
    }

    private PhongDTO convertToDTO(PhongEntity phong) {
        return PhongDTO.builder()
                .idPhong(phong.getIdPhong())
                .idKhoa(phong.getKhoa().getIdKhoa())
                .soPhong(phong.getSoPhong())
                .tang(phong.getTang())
                .trangThai(phong.getTrangThai())
                .build();
    }
} 