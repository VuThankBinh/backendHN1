package com.datn.backendHN.controller;

import com.datn.backendHN.entity.VaccinationRegistration;
import com.datn.backendHN.service.VaccinationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccination-registrations")
public class VaccinationRegistrationController {
    @Autowired
    private VaccinationRegistrationService registrationService;

    @GetMapping
    public List<VaccinationRegistration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public VaccinationRegistration getRegistrationById(@PathVariable Long id) {
        return registrationService.getRegistrationById(id);
    }

    @GetMapping("/calendar/{calendarId}")
    public List<VaccinationRegistration> getRegistrationsByCalendar(@PathVariable Long calendarId) {
        return registrationService.getRegistrationsByCalendar(calendarId);
    }

    @GetMapping("/patient/{maHoSo}")
    public List<VaccinationRegistration> getRegistrationsByPatient(@PathVariable Integer maHoSo) {
        return registrationService.getRegistrationsByPatient(maHoSo);
    }

    @PostMapping
    public VaccinationRegistration createRegistration(@RequestBody VaccinationRegistration registration) {
        return registrationService.createRegistration(registration);
    }

    @PutMapping("/{id}")
    public VaccinationRegistration updateRegistration(@PathVariable Long id, @RequestBody VaccinationRegistration registration) {
        return registrationService.updateRegistration(id, registration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{status}")
    public List<VaccinationRegistration> getRegistrationsByStatus(@PathVariable String status) {
        return registrationService.getRegistrationsByStatus(status);
    }

    @GetMapping("/active")
    public List<VaccinationRegistration> getActiveRegistrations() {
        return registrationService.getActiveRegistrations();
    }
} 