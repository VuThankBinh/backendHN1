package com.datn.backendHN.service;

import com.datn.backendHN.dto.DichVuDTO;

import java.util.List;

public interface DichVuService {
    DichVuDTO createDichVu(DichVuDTO dichVuDTO);
    DichVuDTO updateDichVu(Integer id, DichVuDTO dichVuDTO);
    void deleteDichVu(Integer id);
    DichVuDTO getDichVuById(Integer id);
    List<DichVuDTO> getAllDichVu();
    List<DichVuDTO> getDichVuByLoai(String loaiDichVu);
    List<DichVuDTO> getDichVuByTrangThai(String trangThai);
} 