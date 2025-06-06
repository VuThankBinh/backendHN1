package com.datn.backendHN.repository;

import com.datn.backendHN.entity.GoiKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoiKhamRepository extends JpaRepository<GoiKham, Integer> {
} 