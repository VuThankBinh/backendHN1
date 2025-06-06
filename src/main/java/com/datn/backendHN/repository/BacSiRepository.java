package com.datn.backendHN.repository;

import com.datn.backendHN.entity.BacSiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacSiRepository extends JpaRepository<BacSiEntity, Integer> {
    boolean existsBySoGiayPhep(String soGiayPhep);
} 