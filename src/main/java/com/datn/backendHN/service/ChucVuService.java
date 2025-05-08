package com.datn.backendHN.service;

import com.datn.backendHN.dto.ChucVuDTO;
import com.datn.backendHN.entity.ChucVu;
import java.util.List;

public interface ChucVuService {
    ChucVu createChucVu(ChucVuDTO chucVuDTO);
    ChucVu updateChucVu(Integer id, ChucVuDTO chucVuDTO);
    void deleteChucVu(Integer id);
    ChucVu getChucVuById(Integer id);
    List<ChucVu> getAllChucVu();
} 