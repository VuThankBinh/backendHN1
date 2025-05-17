package com.datn.backendHN.repository;

import com.datn.backendHN.entity.PhongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<PhongEntity, Integer> {
    List<PhongEntity> findByKhoaIdKhoa(Integer idKhoa);
    boolean existsBySoPhongAndKhoaIdKhoa(String soPhong, Integer idKhoa);
} 