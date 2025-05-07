package com.datn.backendHN.repository;

import com.datn.backendHN.entity.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongKhamRepository extends JpaRepository<PhongKham, Integer> {
} 