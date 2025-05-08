package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.LichTiemDTO;
import com.datn.backendHN.entity.LichTiem;
import com.datn.backendHN.repository.LichTiemRepository;
import com.datn.backendHN.service.LichTiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LichTiemServiceImpl implements LichTiemService {

    @Autowired
    private LichTiemRepository lichTiemRepository;

    @Override
    public LichTiemDTO createLichTiem(LichTiemDTO lichTiemDTO) {
        validateLichTiem(lichTiemDTO);
        LichTiem lichTiem = convertToEntity(lichTiemDTO);
        LichTiem savedLichTiem = lichTiemRepository.save(lichTiem);
        return convertToDTO(savedLichTiem);
    }

    @Override
    public LichTiemDTO updateLichTiem(Integer id, LichTiemDTO lichTiemDTO) {
        LichTiem existingLichTiem = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lịch tiêm không tồn tại"));
        
        validateLichTiem(lichTiemDTO);
        
        existingLichTiem.setBacSiId(lichTiemDTO.getBacSiId());
        existingLichTiem.setLoaiVaccine(lichTiemDTO.getLoaiVaccine());
        existingLichTiem.setNgayTiem(lichTiemDTO.getNgayTiem());
        existingLichTiem.setGioTiem(lichTiemDTO.getGioTiem());
        existingLichTiem.setTrangThai(lichTiemDTO.getTrangThai());
        existingLichTiem.setGhiChu(lichTiemDTO.getGhiChu());
        
        LichTiem updatedLichTiem = lichTiemRepository.save(existingLichTiem);
        return convertToDTO(updatedLichTiem);
    }

    @Override
    public void deleteLichTiem(Integer id) {
        if (!lichTiemRepository.existsById(id)) {
            throw new RuntimeException("Lịch tiêm không tồn tại");
        }
        lichTiemRepository.deleteById(id);
    }

    @Override
    public LichTiemDTO getLichTiemById(Integer id) {
        LichTiem lichTiem = lichTiemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lịch tiêm không tồn tại"));
        return convertToDTO(lichTiem);
    }

    @Override
    public List<LichTiemDTO> getLichTiemByBacSiId(Integer bacSiId) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByBacSiId(bacSiId);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByBacSiIdAndNgay(Integer bacSiId, LocalDate ngay) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByBacSiIdAndNgayTiem(bacSiId, ngay);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByNgay(LocalDate ngay) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByNgayTiem(ngay);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByKhoangThoiGian(LocalDate startDate, LocalDate endDate) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByNgayTiemBetween(startDate, endDate);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByBacSiIdAndKhoangThoiGian(Integer bacSiId, LocalDate startDate, LocalDate endDate) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByBacSiIdAndNgayTiemBetween(bacSiId, startDate, endDate);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByLoaiVaccine(String loaiVaccine) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByLoaiVaccine(loaiVaccine);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichTiemDTO> getLichTiemByLoaiVaccineAndKhoangThoiGian(String loaiVaccine, LocalDate startDate, LocalDate endDate) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByLoaiVaccineAndNgayTiemBetween(loaiVaccine, startDate, endDate);
        return lichTiemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getBacSiCoLichTrongKhoangThoiGian(LocalDate startDate, LocalDate endDate) {
        List<LichTiem> lichTiemList = lichTiemRepository.findByNgayTiemBetween(startDate, endDate);
        return lichTiemList.stream()
                .map(LichTiem::getBacSiId)
                .distinct()
                .collect(Collectors.toList());
    }

    private void validateLichTiem(LichTiemDTO lichTiemDTO) {
        if (lichTiemDTO.getBacSiId() == null) {
            throw new RuntimeException("ID bác sĩ không được để trống");
        }
        if (lichTiemDTO.getLoaiVaccine() == null || lichTiemDTO.getLoaiVaccine().trim().isEmpty()) {
            throw new RuntimeException("Loại vaccine không được để trống");
        }
        if (lichTiemDTO.getNgayTiem() == null) {
            throw new RuntimeException("Ngày tiêm không được để trống");
        }
        if (lichTiemDTO.getGioTiem() == null) {
            throw new RuntimeException("Giờ tiêm không được để trống");
        }
        if (lichTiemDTO.getTrangThai() == null || lichTiemDTO.getTrangThai().trim().isEmpty()) {
            throw new RuntimeException("Trạng thái không được để trống");
        }
    }

    private LichTiem convertToEntity(LichTiemDTO dto) {
        LichTiem entity = new LichTiem();
        entity.setId(dto.getId());
        entity.setBacSiId(dto.getBacSiId());
        entity.setLoaiVaccine(dto.getLoaiVaccine());
        entity.setNgayTiem(dto.getNgayTiem());
        entity.setGioTiem(dto.getGioTiem());
        entity.setTrangThai(dto.getTrangThai());
        entity.setGhiChu(dto.getGhiChu());
        return entity;
    }

    private LichTiemDTO convertToDTO(LichTiem entity) {
        LichTiemDTO dto = new LichTiemDTO();
        dto.setId(entity.getId());
        dto.setBacSiId(entity.getBacSiId());
        dto.setLoaiVaccine(entity.getLoaiVaccine());
        dto.setNgayTiem(entity.getNgayTiem());
        dto.setGioTiem(entity.getGioTiem());
        dto.setTrangThai(entity.getTrangThai());
        dto.setGhiChu(entity.getGhiChu());
        return dto;
    }
} 