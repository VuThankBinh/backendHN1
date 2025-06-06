package com.datn.backendHN.repository;

import com.datn.backendHN.entity.LichBacSiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichBacSiRepository extends JpaRepository<LichBacSiEntity, Integer> {
    List<LichBacSiEntity> findByBacSiId(Integer idBacSi);
    List<LichBacSiEntity> findByBacSiIdAndDangKhaDung(Integer idBacSi, Boolean dangKhaDung);
} 