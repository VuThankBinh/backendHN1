package com.datn.backendHN.repository;

import com.datn.backendHN.entity.ChuyenKhoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChuyenKhoaRepository extends JpaRepository<ChuyenKhoaEntity, Integer> {
    List<ChuyenKhoaEntity> findByKhoaId(Integer khoaId);
} 