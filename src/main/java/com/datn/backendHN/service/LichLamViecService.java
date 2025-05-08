package com.datn.backendHN.service;

import com.datn.backendHN.dto.LichLamViecDTO;

import java.time.LocalDate;
import java.util.List;

public interface LichLamViecService {
    LichLamViecDTO createLichLamViec(LichLamViecDTO lichLamViecDTO);
    LichLamViecDTO updateLichLamViec(Integer id, LichLamViecDTO lichLamViecDTO);
    void deleteLichLamViec(Integer id);
    LichLamViecDTO getLichLamViecById(Integer id);
    List<LichLamViecDTO> getLichLamViecByBacSiId(Integer bacSiId);
    List<LichLamViecDTO> getLichLamViecByBacSiIdAndNgay(Integer bacSiId, LocalDate ngay);
} 