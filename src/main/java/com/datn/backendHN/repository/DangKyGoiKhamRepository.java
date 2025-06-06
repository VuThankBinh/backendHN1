package com.datn.backendHN.repository;

import com.datn.backendHN.entity.DangKyGoiKham;
import com.datn.backendHN.enums.TrangThaiDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DangKyGoiKhamRepository extends JpaRepository<DangKyGoiKham, Integer> {
    List<DangKyGoiKham> findByUserId(Integer userId);
    List<DangKyGoiKham> findByTrangThai(TrangThaiDangKy trangThai);
    List<DangKyGoiKham> findByUserIdAndTrangThai(Integer userId, TrangThaiDangKy trangThai);
    List<DangKyGoiKham> findByTrangThaiOrderByNgayDangKyDesc(TrangThaiDangKy trangThai);
    
    // Kiểm tra xem đã có đăng ký nào cho gói khám này vào giờ và ngày này chưa
    boolean existsByMaGoiAndNgayThucHienAndGioKhamAndTrangThaiNot(
            Integer maGoi, 
            LocalDate ngayThucHien, 
            String gioKham,
            TrangThaiDangKy trangThai
    );
} 