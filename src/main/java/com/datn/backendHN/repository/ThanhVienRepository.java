package com.datn.backendHN.repository;

import com.datn.backendHN.entity.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    Optional<ThanhVien> findByTenDangNhap(String tenDangNhap);
    Optional<ThanhVien> findByEmail(String email);
    boolean existsByTenDangNhap(String tenDangNhap);
    boolean existsByEmail(String email);
    boolean existsBySoDienThoai(String soDienThoai);

    @Query("SELECT t FROM ThanhVien t WHERE t.tenDangNhap = :usernameOrEmail OR t.email = :usernameOrEmail")
    Optional<ThanhVien> findByTenDangNhapOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
