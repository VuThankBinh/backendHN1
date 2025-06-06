package com.datn.backendHN.repository;

import com.datn.backendHN.entity.GoiKhamTrucTiepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoiKhamTrucTiepRepository extends JpaRepository<GoiKhamTrucTiepEntity, Integer> {
    
    @Query("SELECT g FROM GoiKhamTrucTiepEntity g WHERE " +
           "(:tenGoiKham IS NULL OR LOWER(g.tenGoiKham) LIKE LOWER(CONCAT('%', :tenGoiKham, '%'))) AND " +
           "(:idChuyenKhoa IS NULL OR g.chuyenKhoa.idChuyenKhoa = :idChuyenKhoa) AND " +
           "(:trangThai IS NULL OR g.trangThai = :trangThai)")
    List<GoiKhamTrucTiepEntity> searchGoiKham(
            @Param("tenGoiKham") String tenGoiKham,
            @Param("idChuyenKhoa") Integer idChuyenKhoa,
            @Param("trangThai") Boolean trangThai
    );

    List<GoiKhamTrucTiepEntity> findByChuyenKhoaIdChuyenKhoa(Integer idChuyenKhoa);
} 