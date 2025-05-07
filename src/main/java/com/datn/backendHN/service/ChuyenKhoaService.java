package com.datn.backendHN.service;

import com.datn.backendHN.dto.ChuyenKhoaDTO;
import java.util.List;

public interface ChuyenKhoaService {
    List<ChuyenKhoaDTO> getAllChuyenKhoa();
    List<ChuyenKhoaDTO> getChuyenKhoaByKhoaId(Integer khoaId);
    ChuyenKhoaDTO getChuyenKhoaById(Integer id);
    ChuyenKhoaDTO createChuyenKhoa(ChuyenKhoaDTO chuyenKhoaDTO);
    ChuyenKhoaDTO updateChuyenKhoa(Integer id, ChuyenKhoaDTO chuyenKhoaDTO);
    void deleteChuyenKhoa(Integer id);
} 