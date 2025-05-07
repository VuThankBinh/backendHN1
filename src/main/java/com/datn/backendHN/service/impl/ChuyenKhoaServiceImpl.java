package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.ChuyenKhoaDTO;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.KhoaRepository;
import com.datn.backendHN.service.ChuyenKhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChuyenKhoaServiceImpl implements ChuyenKhoaService {

    @Autowired
    private ChuyenKhoaRepository chuyenKhoaRepository;

    @Autowired
    private KhoaRepository khoaRepository;

    @Override
    public List<ChuyenKhoaDTO> getAllChuyenKhoa() {
        return chuyenKhoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChuyenKhoaDTO> getChuyenKhoaByKhoaId(Integer khoaId) {
        return chuyenKhoaRepository.findByKhoaId(khoaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChuyenKhoaDTO getChuyenKhoaById(Integer id) {
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChuyenKhoa not found with id: " + id));
        return convertToDTO(chuyenKhoa);
    }

    @Override
    public ChuyenKhoaDTO createChuyenKhoa(ChuyenKhoaDTO chuyenKhoaDTO) {
        KhoaEntity khoa = khoaRepository.findById(chuyenKhoaDTO.getKhoaId())
                .orElseThrow(() -> new RuntimeException("Khoa not found with id: " + chuyenKhoaDTO.getKhoaId()));
        
        ChuyenKhoaEntity chuyenKhoa = convertToEntity(chuyenKhoaDTO);
        chuyenKhoa.setKhoa(khoa);
        
        ChuyenKhoaEntity savedChuyenKhoa = chuyenKhoaRepository.save(chuyenKhoa);
        return convertToDTO(savedChuyenKhoa);
    }

    @Override
    public ChuyenKhoaDTO updateChuyenKhoa(Integer id, ChuyenKhoaDTO chuyenKhoaDTO) {
        ChuyenKhoaEntity existingChuyenKhoa = chuyenKhoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChuyenKhoa not found with id: " + id));
        
        KhoaEntity khoa = khoaRepository.findById(chuyenKhoaDTO.getKhoaId())
                .orElseThrow(() -> new RuntimeException("Khoa not found with id: " + chuyenKhoaDTO.getKhoaId()));
        
        existingChuyenKhoa.setTenChuyenKhoa(chuyenKhoaDTO.getTenChuyenKhoa());
        existingChuyenKhoa.setMoTa(chuyenKhoaDTO.getMoTa());
        existingChuyenKhoa.setKhoa(khoa);
        
        ChuyenKhoaEntity updatedChuyenKhoa = chuyenKhoaRepository.save(existingChuyenKhoa);
        return convertToDTO(updatedChuyenKhoa);
    }

    @Override
    public void deleteChuyenKhoa(Integer id) {
        if (!chuyenKhoaRepository.existsById(id)) {
            throw new RuntimeException("ChuyenKhoa not found with id: " + id);
        }
        chuyenKhoaRepository.deleteById(id);
    }

    private ChuyenKhoaDTO convertToDTO(ChuyenKhoaEntity chuyenKhoa) {
        return new ChuyenKhoaDTO(
            chuyenKhoa.getId(),
            chuyenKhoa.getTenChuyenKhoa(),
            chuyenKhoa.getMoTa(),
            chuyenKhoa.getKhoa().getId()
        );
    }

    private ChuyenKhoaEntity convertToEntity(ChuyenKhoaDTO chuyenKhoaDTO) {
        ChuyenKhoaEntity chuyenKhoa = new ChuyenKhoaEntity();
        chuyenKhoa.setId(chuyenKhoaDTO.getId());
        chuyenKhoa.setTenChuyenKhoa(chuyenKhoaDTO.getTenChuyenKhoa());
        chuyenKhoa.setMoTa(chuyenKhoaDTO.getMoTa());
        return chuyenKhoa;
    }
} 