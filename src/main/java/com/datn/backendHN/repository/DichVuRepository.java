package com.datn.backendHN.repository;

import com.datn.backendHN.entity.DichVuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DichVuRepository extends JpaRepository<DichVuEntity, Integer> {
    List<DichVuEntity> findByChuyenKhoaIdChuyenKhoa(Integer idChuyenKhoa);
    boolean existsByTenDichVuAndChuyenKhoaIdChuyenKhoa(String tenDichVu, Integer idChuyenKhoa);
} 