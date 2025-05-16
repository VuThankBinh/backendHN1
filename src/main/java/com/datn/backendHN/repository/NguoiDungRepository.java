package com.datn.backendHN.repository;

import com.datn.backendHN.entity.NguoiDungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDungEntity, Integer> {
    Optional<NguoiDungEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCccd(String cccd);
}
