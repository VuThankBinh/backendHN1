package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.KhoaDTO;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.repository.KhoaRepository;
import com.datn.backendHN.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhoaServiceImpl implements KhoaService {

    @Autowired
    private KhoaRepository khoaRepository;

    @Override
    public List<KhoaDTO> getAllKhoa() {
        return khoaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public KhoaDTO getKhoaById(Integer id) {
        KhoaEntity khoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoa not found with id: " + id));
        return convertToDTO(khoa);
    }

    @Override
    public KhoaDTO createKhoa(KhoaDTO khoaDTO) {
        KhoaEntity khoa = convertToEntity(khoaDTO);
        KhoaEntity savedKhoa = khoaRepository.save(khoa);
        return convertToDTO(savedKhoa);
    }

    @Override
    public KhoaDTO updateKhoa(Integer id, KhoaDTO khoaDTO) {
        KhoaEntity existingKhoa = khoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoa not found with id: " + id));
        
        existingKhoa.setTenKhoa(khoaDTO.getTenKhoa());
        existingKhoa.setMoTa(khoaDTO.getMoTa());
        
        KhoaEntity updatedKhoa = khoaRepository.save(existingKhoa);
        return convertToDTO(updatedKhoa);
    }

    @Override
    public void deleteKhoa(Integer id) {
        if (!khoaRepository.existsById(id)) {
            throw new RuntimeException("Khoa not found with id: " + id);
        }
        khoaRepository.deleteById(id);
    }

    private KhoaDTO convertToDTO(KhoaEntity khoa) {
        return new KhoaDTO(
            khoa.getId(),
            khoa.getTenKhoa(),
            khoa.getMoTa()
        );
    }

    private KhoaEntity convertToEntity(KhoaDTO khoaDTO) {
        KhoaEntity khoa = new KhoaEntity();
        khoa.setId(khoaDTO.getId());
        khoa.setTenKhoa(khoaDTO.getTenKhoa());
        khoa.setMoTa(khoaDTO.getMoTa());
        return khoa;
    }
} 