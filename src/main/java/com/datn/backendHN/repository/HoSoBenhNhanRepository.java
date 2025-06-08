package com.datn.backendHN.repository;

import com.datn.backendHN.entity.HoSoBenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HoSoBenhNhanRepository extends JpaRepository<HoSoBenhNhan, Integer> {
    List<HoSoBenhNhan> findByNguoiDungId(Integer nguoiDungId);
    Boolean existsByMaDinhDanh(String maDinhDanh);
    Boolean existsBySoDienThoai(String soDienThoai);
    HoSoBenhNhan findByMaDinhDanh(String maDinhDanh);
    HoSoBenhNhan findBySoDienThoai(String soDienThoai);
} 