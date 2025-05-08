package com.datn.backendHN.service;

import com.datn.backendHN.dto.LeTanDTO;
import com.datn.backendHN.entity.ThanhVien;

import java.util.List;

public interface LeTanService {
    ThanhVien createLeTan(LeTanDTO leTanDTO);
    ThanhVien updateLeTan(Integer id, LeTanDTO leTanDTO);
    void deleteLeTan(Integer id);
    ThanhVien getLeTanById(Integer id);
    List<ThanhVien> getAllLeTan();
} 