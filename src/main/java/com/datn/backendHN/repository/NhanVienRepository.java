package com.datn.backendHN.repository;

import com.datn.backendHN.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    List<NhanVien> findByChucVuId(Integer chucVuId);
} 