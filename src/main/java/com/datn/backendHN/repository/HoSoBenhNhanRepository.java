package com.datn.backendHN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datn.backendHN.entity.HoSoBenhNhan;

import java.util.List;

@Repository
public interface HoSoBenhNhanRepository extends JpaRepository<HoSoBenhNhan, Integer> {
    List<HoSoBenhNhan> findByNguoiDungId(Integer nguoiDungId);
    boolean existsByMaDinhDanh(String maDinhDanh);
} 