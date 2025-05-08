package com.datn.backendHN.repository;

import com.datn.backendHN.entity.BacSi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacSiRepository extends JpaRepository<BacSi, Integer> {
    List<BacSi> findByChuyenKhoaId(Integer chuyenKhoaId);
    List<BacSi> findByPhongKhamId(Integer phongKhamId);
} 