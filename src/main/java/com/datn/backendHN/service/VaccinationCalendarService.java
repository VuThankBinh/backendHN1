package com.datn.backendHN.service;

import com.datn.backendHN.entity.VaccinationCalendar;
import com.datn.backendHN.repository.VaccinationCalendarRepository;
import com.datn.backendHN.repository.VaccineRepository;
import com.datn.backendHN.exception.VaccinationCalendarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCalendarService {
    @Autowired
    private VaccinationCalendarRepository calendarRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    private static final int MIN_DAYS_AHEAD = 3; // Số ngày tối thiểu phải đặt trước

    public List<VaccinationCalendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

    public Optional<VaccinationCalendar> getCalendarById(Long id) {
        return calendarRepository.findById(id);
    }

    public List<VaccinationCalendar> getCalendarsByDate(LocalDate date) {
        return calendarRepository.findByVaccinationDateAndIsActiveTrue(date);
    }

    public List<VaccinationCalendar> getCalendarsByDateRange(LocalDate startDate, LocalDate endDate) {
        return calendarRepository.findByVaccinationDateBetweenAndIsActiveTrue(startDate, endDate);
    }

    private void validateVaccinationDate(LocalDate vaccinationDate) {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, vaccinationDate);
        
        if (daysBetween < MIN_DAYS_AHEAD) {
            throw new VaccinationCalendarException("Ngày tiêm phải cách ngày hiện tại ít nhất " + MIN_DAYS_AHEAD + " ngày");
        }

        if (calendarRepository.existsByVaccinationDateAndIsActiveTrue(vaccinationDate)) {
            throw new VaccinationCalendarException("Đã có lịch tiêm vaccine vào ngày " + vaccinationDate + ". Vui lòng chọn ngày khác.");
        }
    }

    @Transactional
    public VaccinationCalendar createCalendar(VaccinationCalendar calendar) {
        // Kiểm tra vaccine tồn tại
        if (!vaccineRepository.existsById(calendar.getVaccine().getId())) {
            throw new VaccinationCalendarException("Không tìm thấy vaccine với ID: " + calendar.getVaccine().getId());
        }

        // Validate ngày tiêm
        validateVaccinationDate(calendar.getVaccinationDate());

        // Load đầy đủ thông tin vaccine
        calendar.setVaccine(vaccineRepository.findById(calendar.getVaccine().getId()).get());
        
        // Set giá trị mặc định
        calendar.setCurrentPatients(0);
        calendar.setIsActive(true);

        return calendarRepository.save(calendar);
    }

    @Transactional
    public VaccinationCalendar updateCalendar(Long id, VaccinationCalendar calendarDetails) {
        VaccinationCalendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch tiêm"));

        // Kiểm tra vaccine tồn tại
        if (!vaccineRepository.existsById(calendarDetails.getVaccine().getId())) {
            throw new RuntimeException("Không tìm thấy vaccine");
        }

        // Kiểm tra nếu thay đổi ngày
        if (!calendar.getVaccinationDate().equals(calendarDetails.getVaccinationDate())) {
            validateVaccinationDate(calendarDetails.getVaccinationDate());
        }

        // Load đầy đủ thông tin vaccine
        calendar.setVaccine(vaccineRepository.findById(calendarDetails.getVaccine().getId()).get());

        // Cập nhật các trường
        calendar.setVaccinationDate(calendarDetails.getVaccinationDate());
        calendar.setStartTime(calendarDetails.getStartTime());
        calendar.setEndTime(calendarDetails.getEndTime());
        calendar.setMaxPatients(calendarDetails.getMaxPatients());
        calendar.setNotes(calendarDetails.getNotes());
        
        // Cập nhật trạng thái
        calendar.setIsActive(calendarDetails.getIsActive());

        return calendarRepository.save(calendar);
    }

    @Transactional
    public void deleteCalendar(Long id) {
        VaccinationCalendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch tiêm"));

        // Kiểm tra xem có đăng ký nào không
        if (calendar.getCurrentPatients() > 0) {
            throw new RuntimeException("Không thể xóa lịch tiêm đã có người đăng ký");
        }

        calendarRepository.delete(calendar);
    }

    public List<VaccinationCalendar> getCalendarsByVaccine(Long vaccineId) {
        return calendarRepository.findByVaccineId(vaccineId);
    }

    public List<VaccinationCalendar> getCalendarsByVaccineAndStatus(Long vaccineId, Boolean isActive) {
        return calendarRepository.findByVaccineIdAndIsActive(vaccineId, isActive);
    }

    public List<VaccinationCalendar> getCalendarsByVaccineAndDateRange(Long vaccineId, LocalDate startDate, LocalDate endDate) {
        return calendarRepository.findByVaccineIdAndVaccinationDateBetween(vaccineId, startDate, endDate);
    }

    public List<VaccinationCalendar> getActiveCalendars() {
        return calendarRepository.findByIsActiveTrue();
    }

    public void incrementCurrentPatients(Long id) {
        VaccinationCalendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch tiêm"));
        
        if (calendar.getCurrentPatients() >= calendar.getMaxPatients()) {
            throw new RuntimeException("Đã đạt số lượng bệnh nhân tối đa cho lịch tiêm này");
        }
        
        calendar.setCurrentPatients(calendar.getCurrentPatients() + 1);
        calendarRepository.save(calendar);
    }

    public void decrementCurrentPatients(Long id) {
        VaccinationCalendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch tiêm"));
        
        if (calendar.getCurrentPatients() > 0) {
            calendar.setCurrentPatients(calendar.getCurrentPatients() - 1);
            calendarRepository.save(calendar);
        }
    }
} 