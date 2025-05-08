package com.datn.backendHN.repository;

import com.datn.backendHN.entity.LichLamViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LichLamViecRepository extends JpaRepository<LichLamViec, Integer> {
    List<LichLamViec> findByBacSiId(Integer bacSiId);
    List<LichLamViec> findByBacSiIdAndNgay(Integer bacSiId, LocalDate ngay);
} 