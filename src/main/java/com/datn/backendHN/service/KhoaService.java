package com.datn.backendHN.service;

import com.datn.backendHN.dto.KhoaDTO;
import java.util.List;

public interface KhoaService {
    List<KhoaDTO> getAllKhoa();
    KhoaDTO getKhoaById(Integer id);
    KhoaDTO createKhoa(KhoaDTO khoaDTO);
    KhoaDTO updateKhoa(Integer id, KhoaDTO khoaDTO);
    void deleteKhoa(Integer id);
} 