package com.datn.backendHN.repository;

import com.datn.backendHN.entity.LichTiem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LichTiemRepository extends JpaRepository<LichTiem, Integer> {
    List<LichTiem> findByBacSiId(Integer bacSiId);
    List<LichTiem> findByBacSiIdAndNgayTiem(Integer bacSiId, LocalDate ngayTiem);
    List<LichTiem> findByNgayTiem(LocalDate ngayTiem);
    List<LichTiem> findByNgayTiemBetween(LocalDate startDate, LocalDate endDate);
    List<LichTiem> findByBacSiIdAndNgayTiemBetween(Integer bacSiId, LocalDate startDate, LocalDate endDate);
    List<LichTiem> findByLoaiVaccine(String loaiVaccine);
    List<LichTiem> findByLoaiVaccineAndNgayTiemBetween(String loaiVaccine, LocalDate startDate, LocalDate endDate);
    List<LichTiem> findByNgayTiemBetweenAndBacSiIdIn(LocalDate startDate, LocalDate endDate, List<Integer> bacSiIds);
} 