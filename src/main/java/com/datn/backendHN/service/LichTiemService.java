package com.datn.backendHN.service;

import com.datn.backendHN.dto.LichTiemDTO;

import java.time.LocalDate;
import java.util.List;

public interface LichTiemService {
    LichTiemDTO createLichTiem(LichTiemDTO lichTiemDTO);
    LichTiemDTO updateLichTiem(Integer id, LichTiemDTO lichTiemDTO);
    void deleteLichTiem(Integer id);
    LichTiemDTO getLichTiemById(Integer id);
    List<LichTiemDTO> getLichTiemByBacSiId(Integer bacSiId);
    List<LichTiemDTO> getLichTiemByBacSiIdAndNgay(Integer bacSiId, LocalDate ngay);
    List<LichTiemDTO> getLichTiemByNgay(LocalDate ngay);
    List<LichTiemDTO> getLichTiemByKhoangThoiGian(LocalDate startDate, LocalDate endDate);
    List<LichTiemDTO> getLichTiemByBacSiIdAndKhoangThoiGian(Integer bacSiId, LocalDate startDate, LocalDate endDate);
    List<LichTiemDTO> getLichTiemByLoaiVaccine(String loaiVaccine);
    List<LichTiemDTO> getLichTiemByLoaiVaccineAndKhoangThoiGian(String loaiVaccine, LocalDate startDate, LocalDate endDate);
    List<Integer> getBacSiCoLichTrongKhoangThoiGian(LocalDate startDate, LocalDate endDate);
} 