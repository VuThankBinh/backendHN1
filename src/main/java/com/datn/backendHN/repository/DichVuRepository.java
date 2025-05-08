package com.datn.backendHN.repository;

import com.datn.backendHN.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
    List<DichVu> findByLoaiDichVu(String loaiDichVu);
    List<DichVu> findByTrangThai(String trangThai);
} 