package com.datn.backendHN.repository;

import com.datn.backendHN.entity.VaccinationCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationCalendarRepository extends JpaRepository<VaccinationCalendar, Long> {
    List<VaccinationCalendar> findByVaccinationDateAndIsActiveTrue(LocalDate date);
    List<VaccinationCalendar> findByVaccinationDateBetweenAndIsActiveTrue(LocalDate startDate, LocalDate endDate);
    List<VaccinationCalendar> findByVaccineIdAndIsActiveTrue(Long vaccineId);
    boolean existsByVaccinationDateAndVaccineIdAndIsActiveTrue(LocalDate date, Long vaccineId);
    List<VaccinationCalendar> findByVaccineId(Long vaccineId);
    List<VaccinationCalendar> findByIsActiveTrue();
    boolean existsByVaccinationDateAndIsActiveTrue(LocalDate date);
    
    // Thêm các phương thức mới
    List<VaccinationCalendar> findByVaccineIdAndIsActive(Long vaccineId, Boolean isActive);
    List<VaccinationCalendar> findByVaccineIdAndVaccinationDateBetween(Long vaccineId, LocalDate startDate, LocalDate endDate);
} 