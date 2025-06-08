package com.datn.backendHN.repository;

import com.datn.backendHN.entity.VaccinationRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationRegistrationRepository extends JpaRepository<VaccinationRegistration, Long> {
    List<VaccinationRegistration> findByCalendarId(Long calendarId);
    List<VaccinationRegistration> findByMaHoSo(Integer maHoSo);
    List<VaccinationRegistration> findByStatus(String status);
    List<VaccinationRegistration> findByIsActiveTrue();
    boolean existsByCalendarIdAndMaHoSoAndIsActiveTrue(Long calendarId, Integer maHoSo);
} 