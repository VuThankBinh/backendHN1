package com.datn.backendHN.repository;

import com.datn.backendHN.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    boolean existsByName(String name);
    
    List<Vaccine> findByNameContainingAndManufacturerContainingAndIsActive(String name, String manufacturer, Boolean isActive);
    List<Vaccine> findByNameContainingAndManufacturerContaining(String name, String manufacturer);
    List<Vaccine> findByNameContainingAndIsActive(String name, Boolean isActive);
    List<Vaccine> findByManufacturerContainingAndIsActive(String manufacturer, Boolean isActive);
    List<Vaccine> findByNameContaining(String name);
    List<Vaccine> findByManufacturerContaining(String manufacturer);
    List<Vaccine> findByIsActive(Boolean isActive);
} 