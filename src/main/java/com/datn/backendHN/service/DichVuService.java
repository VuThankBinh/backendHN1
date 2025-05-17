package com.datn.backendHN.service;

import com.datn.backendHN.dto.DichVuDTO;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.DichVuEntity;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.DichVuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DichVuService {
    private final DichVuRepository dichVuRepository;
    private final ChuyenKhoaRepository chuyenKhoaRepository;

    public List<DichVuDTO> getAllDichVu() {
        return dichVuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DichVuDTO> getDichVuByChuyenKhoaId(Integer chuyenKhoaId) {
        return dichVuRepository.findByChuyenKhoaIdChuyenKhoa(chuyenKhoaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DichVuDTO getDichVuById(Integer id) {
        return dichVuRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dịch vụ"));
    }

    @Transactional
    public DichVuDTO createDichVu(DichVuDTO dichVuDTO) {
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(dichVuDTO.getIdChuyenKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));

        if (dichVuRepository.existsByTenDichVuAndChuyenKhoaIdChuyenKhoa(
                dichVuDTO.getTenDichVu(), dichVuDTO.getIdChuyenKhoa())) {
            throw new RuntimeException("Tên dịch vụ đã tồn tại trong chuyên khoa này");
        }

        DichVuEntity dichVu = DichVuEntity.builder()
                .chuyenKhoa(chuyenKhoa)
                .tenDichVu(dichVuDTO.getTenDichVu())
                .moTa(dichVuDTO.getMoTa())
                .gia(dichVuDTO.getGia())
                .thoiGianDuKien(dichVuDTO.getThoiGianDuKien())
                .daKichHoat(dichVuDTO.getDaKichHoat())
                .build();

        return convertToDTO(dichVuRepository.save(dichVu));
    }

    @Transactional
    public DichVuDTO updateDichVu(Integer id, DichVuDTO dichVuDTO) {
        DichVuEntity dichVu = dichVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dịch vụ"));

        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(dichVuDTO.getIdChuyenKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));

        if (!dichVu.getTenDichVu().equals(dichVuDTO.getTenDichVu()) &&
                dichVuRepository.existsByTenDichVuAndChuyenKhoaIdChuyenKhoa(
                        dichVuDTO.getTenDichVu(), dichVuDTO.getIdChuyenKhoa())) {
            throw new RuntimeException("Tên dịch vụ đã tồn tại trong chuyên khoa này");
        }

        dichVu.setChuyenKhoa(chuyenKhoa);
        dichVu.setTenDichVu(dichVuDTO.getTenDichVu());
        dichVu.setMoTa(dichVuDTO.getMoTa());
        dichVu.setGia(dichVuDTO.getGia());
        dichVu.setThoiGianDuKien(dichVuDTO.getThoiGianDuKien());
        dichVu.setDaKichHoat(dichVuDTO.getDaKichHoat());

        return convertToDTO(dichVuRepository.save(dichVu));
    }

    @Transactional
    public void deleteDichVu(Integer id) {
        if (!dichVuRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy dịch vụ");
        }
        dichVuRepository.deleteById(id);
    }

    private DichVuDTO convertToDTO(DichVuEntity dichVu) {
        return DichVuDTO.builder()
                .idDichVu(dichVu.getIdDichVu())
                .idChuyenKhoa(dichVu.getChuyenKhoa().getIdChuyenKhoa())
                .tenDichVu(dichVu.getTenDichVu())
                .moTa(dichVu.getMoTa())
                .gia(dichVu.getGia())
                .thoiGianDuKien(dichVu.getThoiGianDuKien())
                .daKichHoat(dichVu.getDaKichHoat())
                .build();
    }
} 