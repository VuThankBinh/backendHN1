package com.datn.backendHN.service;

import com.datn.backendHN.entity.VaccinationRegistration;
import com.datn.backendHN.repository.VaccinationRegistrationRepository;
import com.datn.backendHN.repository.VaccinationCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VaccinationRegistrationService {
    @Autowired
    private VaccinationRegistrationRepository registrationRepository;

    @Autowired
    private VaccinationCalendarRepository calendarRepository;

    public List<VaccinationRegistration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public VaccinationRegistration getRegistrationById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký tiêm chủng"));
    }

    public List<VaccinationRegistration> getRegistrationsByCalendar(Long calendarId) {
        return registrationRepository.findByCalendarId(calendarId);
    }

    public List<VaccinationRegistration> getRegistrationsByPatient(Integer maHoSo) {
        return registrationRepository.findByMaHoSo(maHoSo);
    }

    @Transactional
    public VaccinationRegistration createRegistration(VaccinationRegistration registration) {
        // Kiểm tra lịch tiêm tồn tại
        if (!calendarRepository.existsById(registration.getCalendar().getId())) {
            throw new RuntimeException("Không tìm thấy lịch tiêm");
        }

        // Kiểm tra đã đăng ký chưa
        if (registrationRepository.existsByCalendarIdAndMaHoSoAndIsActiveTrue(
                registration.getCalendar().getId(), registration.getMaHoSo())) {
            throw new RuntimeException("Bệnh nhân đã đăng ký lịch tiêm này");
        }

        // Set giá trị mặc định
        registration.setRegistrationTime(LocalDateTime.now());
        registration.setStatus("PENDING");
        registration.setIsActive(true);

        return registrationRepository.save(registration);
    }

    @Transactional
    public VaccinationRegistration updateRegistration(Long id, VaccinationRegistration registrationDetails) {
        VaccinationRegistration registration = getRegistrationById(id);

        // Cập nhật các trường
        registration.setStatus(registrationDetails.getStatus());
        registration.setNotes(registrationDetails.getNotes());
        registration.setIsActive(registrationDetails.getIsActive());

        return registrationRepository.save(registration);
    }

    @Transactional
    public void deleteRegistration(Long id) {
        VaccinationRegistration registration = getRegistrationById(id);
        registration.setIsActive(false);
        registrationRepository.save(registration);
    }

    public List<VaccinationRegistration> getRegistrationsByStatus(String status) {
        return registrationRepository.findByStatus(status);
    }

    public List<VaccinationRegistration> getActiveRegistrations() {
        return registrationRepository.findByIsActiveTrue();
    }
} 