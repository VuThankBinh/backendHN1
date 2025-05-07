package com.datn.backendHN.repository;

import com.datn.backendHN.entity.KhoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoaRepository extends JpaRepository<KhoaEntity, Integer> {
} 