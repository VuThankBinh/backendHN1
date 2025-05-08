package com.datn.backendHN.repository;

import com.datn.backendHN.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
    Optional<ChucVu> findByTenChucVu(String tenChucVu);
} 