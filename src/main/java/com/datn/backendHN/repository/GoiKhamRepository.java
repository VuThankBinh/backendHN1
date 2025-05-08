package com.datn.backendHN.repository;

import com.datn.backendHN.entity.GoiKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoiKhamRepository extends JpaRepository<GoiKham, Integer> {
    List<GoiKham> findByChuyenKhoaId(Integer chuyenKhoaId);
    List<GoiKham> findByTrangThai(String trangThai);
} 