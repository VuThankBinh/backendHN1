package com.datn.backendHN.repository;

import com.datn.backendHN.entity.LeTanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeTanRepository extends JpaRepository<LeTanEntity, Integer> {
} 