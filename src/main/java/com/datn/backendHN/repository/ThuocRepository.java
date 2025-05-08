package com.datn.backendHN.repository;

import com.datn.backendHN.entity.Thuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuocRepository extends JpaRepository<Thuoc, Integer> {
    List<Thuoc> findByTenThuocContaining(String tenThuoc);
} 