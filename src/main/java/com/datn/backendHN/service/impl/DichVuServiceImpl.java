package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.DichVuDTO;
import com.datn.backendHN.entity.DichVu;
import com.datn.backendHN.repository.DichVuRepository;
import com.datn.backendHN.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DichVuServiceImpl implements DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    @Override
    public DichVuDTO createDichVu(DichVuDTO dichVuDTO) {
        validateDichVu(dichVuDTO);
        DichVu dichVu = convertToEntity(dichVuDTO);
        DichVu savedDichVu = dichVuRepository.save(dichVu);
        return convertToDTO(savedDichVu);
    }

    @Override
    public DichVuDTO updateDichVu(Integer id, DichVuDTO dichVuDTO) {
        DichVu existingDichVu = dichVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dịch vụ không tồn tại"));
        
        validateDichVu(dichVuDTO);
        
        existingDichVu.setTenDichVu(dichVuDTO.getTenDichVu());
        existingDichVu.setMoTa(dichVuDTO.getMoTa());
        existingDichVu.setDonViTinh(dichVuDTO.getDonViTinh());
        existingDichVu.setDonGia(dichVuDTO.getDonGia());
        existingDichVu.setLoaiDichVu(dichVuDTO.getLoaiDichVu());
        existingDichVu.setTrangThai(dichVuDTO.getTrangThai());
        
        DichVu updatedDichVu = dichVuRepository.save(existingDichVu);
        return convertToDTO(updatedDichVu);
    }

    @Override
    public void deleteDichVu(Integer id) {
        if (!dichVuRepository.existsById(id)) {
            throw new RuntimeException("Dịch vụ không tồn tại");
        }
        dichVuRepository.deleteById(id);
    }

    @Override
    public DichVuDTO getDichVuById(Integer id) {
        DichVu dichVu = dichVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dịch vụ không tồn tại"));
        return convertToDTO(dichVu);
    }

    @Override
    public List<DichVuDTO> getAllDichVu() {
        List<DichVu> dichVuList = dichVuRepository.findAll();
        return dichVuList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DichVuDTO> getDichVuByLoai(String loaiDichVu) {
        List<DichVu> dichVuList = dichVuRepository.findByLoaiDichVu(loaiDichVu);
        return dichVuList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DichVuDTO> getDichVuByTrangThai(String trangThai) {
        List<DichVu> dichVuList = dichVuRepository.findByTrangThai(trangThai);
        return dichVuList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private void validateDichVu(DichVuDTO dichVuDTO) {
        if (dichVuDTO.getTenDichVu() == null || dichVuDTO.getTenDichVu().trim().isEmpty()) {
            throw new RuntimeException("Tên dịch vụ không được để trống");
        }
        if (dichVuDTO.getDonViTinh() == null || dichVuDTO.getDonViTinh().trim().isEmpty()) {
            throw new RuntimeException("Đơn vị tính không được để trống");
        }
        if (dichVuDTO.getDonGia() == null || dichVuDTO.getDonGia() <= 0) {
            throw new RuntimeException("Đơn giá phải lớn hơn 0");
        }
        if (dichVuDTO.getLoaiDichVu() == null || dichVuDTO.getLoaiDichVu().trim().isEmpty()) {
            throw new RuntimeException("Loại dịch vụ không được để trống");
        }
        if (dichVuDTO.getTrangThai() == null || dichVuDTO.getTrangThai().trim().isEmpty()) {
            throw new RuntimeException("Trạng thái không được để trống");
        }
    }

    private DichVu convertToEntity(DichVuDTO dto) {
        DichVu entity = new DichVu();
        entity.setId(dto.getId());
        entity.setTenDichVu(dto.getTenDichVu());
        entity.setMoTa(dto.getMoTa());
        entity.setDonViTinh(dto.getDonViTinh());
        entity.setDonGia(dto.getDonGia());
        entity.setLoaiDichVu(dto.getLoaiDichVu());
        entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    private DichVuDTO convertToDTO(DichVu entity) {
        DichVuDTO dto = new DichVuDTO();
        dto.setId(entity.getId());
        dto.setTenDichVu(entity.getTenDichVu());
        dto.setMoTa(entity.getMoTa());
        dto.setDonViTinh(entity.getDonViTinh());
        dto.setDonGia(entity.getDonGia());
        dto.setLoaiDichVu(entity.getLoaiDichVu());
        dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
} 