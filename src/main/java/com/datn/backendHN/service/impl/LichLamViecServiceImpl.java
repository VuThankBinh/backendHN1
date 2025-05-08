package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.LichLamViecDTO;
import com.datn.backendHN.entity.LichLamViec;
import com.datn.backendHN.repository.LichLamViecRepository;
import com.datn.backendHN.service.LichLamViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LichLamViecServiceImpl implements LichLamViecService {

    @Autowired
    private LichLamViecRepository lichLamViecRepository;

    @Override
    public LichLamViecDTO createLichLamViec(LichLamViecDTO lichLamViecDTO) {
        validateLichLamViec(lichLamViecDTO);
        LichLamViec lichLamViec = convertToEntity(lichLamViecDTO);
        LichLamViec savedLichLamViec = lichLamViecRepository.save(lichLamViec);
        return convertToDTO(savedLichLamViec);
    }

    @Override
    public LichLamViecDTO updateLichLamViec(Integer id, LichLamViecDTO lichLamViecDTO) {
        LichLamViec existingLichLamViec = lichLamViecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lịch làm việc không tồn tại"));
        
        validateLichLamViec(lichLamViecDTO);
        
        existingLichLamViec.setBacSiId(lichLamViecDTO.getBacSiId());
        existingLichLamViec.setNgay(lichLamViecDTO.getNgay());
        existingLichLamViec.setGioBatDau(lichLamViecDTO.getGioBatDau());
        existingLichLamViec.setGioKetThuc(lichLamViecDTO.getGioKetThuc());
        
        LichLamViec updatedLichLamViec = lichLamViecRepository.save(existingLichLamViec);
        return convertToDTO(updatedLichLamViec);
    }

    @Override
    public void deleteLichLamViec(Integer id) {
        if (!lichLamViecRepository.existsById(id)) {
            throw new RuntimeException("Lịch làm việc không tồn tại");
        }
        lichLamViecRepository.deleteById(id);
    }

    @Override
    public LichLamViecDTO getLichLamViecById(Integer id) {
        LichLamViec lichLamViec = lichLamViecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lịch làm việc không tồn tại"));
        return convertToDTO(lichLamViec);
    }

    @Override
    public List<LichLamViecDTO> getLichLamViecByBacSiId(Integer bacSiId) {
        List<LichLamViec> lichLamViecList = lichLamViecRepository.findByBacSiId(bacSiId);
        return lichLamViecList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LichLamViecDTO> getLichLamViecByBacSiIdAndNgay(Integer bacSiId, LocalDate ngay) {
        List<LichLamViec> lichLamViecList = lichLamViecRepository.findByBacSiIdAndNgay(bacSiId, ngay);
        return lichLamViecList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void validateLichLamViec(LichLamViecDTO lichLamViecDTO) {
        if (lichLamViecDTO.getBacSiId() == null) {
            throw new RuntimeException("ID bác sĩ không được để trống");
        }
        if (lichLamViecDTO.getNgay() == null) {
            throw new RuntimeException("Ngày không được để trống");
        }
        if (lichLamViecDTO.getGioBatDau() == null) {
            throw new RuntimeException("Giờ bắt đầu không được để trống");
        }
        if (lichLamViecDTO.getGioKetThuc() == null) {
            throw new RuntimeException("Giờ kết thúc không được để trống");
        }
        if (lichLamViecDTO.getGioBatDau().isAfter(lichLamViecDTO.getGioKetThuc())) {
            throw new RuntimeException("Giờ bắt đầu phải trước giờ kết thúc");
        }
    }

    private LichLamViec convertToEntity(LichLamViecDTO dto) {
        LichLamViec entity = new LichLamViec();
        entity.setId(dto.getId());
        entity.setBacSiId(dto.getBacSiId());
        entity.setNgay(dto.getNgay());
        entity.setGioBatDau(dto.getGioBatDau());
        entity.setGioKetThuc(dto.getGioKetThuc());
        return entity;
    }

    private LichLamViecDTO convertToDTO(LichLamViec entity) {
        LichLamViecDTO dto = new LichLamViecDTO();
        dto.setId(entity.getId());
        dto.setBacSiId(entity.getBacSiId());
        dto.setNgay(entity.getNgay());
        dto.setGioBatDau(entity.getGioBatDau());
        dto.setGioKetThuc(entity.getGioKetThuc());
        return dto;
    }
} 