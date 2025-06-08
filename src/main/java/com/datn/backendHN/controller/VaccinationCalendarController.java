package com.datn.backendHN.controller;

import com.datn.backendHN.entity.VaccinationCalendar;
import com.datn.backendHN.service.VaccinationCalendarService;
import com.datn.backendHN.exception.VaccinationCalendarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "*")
public class VaccinationCalendarController {
    @Autowired
    private VaccinationCalendarService calendarService;

    @GetMapping
    public List<VaccinationCalendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinationCalendar> getCalendarById(@PathVariable Long id) {
        return calendarService.getCalendarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<VaccinationCalendar>> getCalendarsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<VaccinationCalendar> calendars = calendarService.getCalendarsByDate(date);
        return ResponseEntity.ok(calendars);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<VaccinationCalendar>> getCalendarsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<VaccinationCalendar> calendars = calendarService.getCalendarsByDateRange(startDate, endDate);
        return ResponseEntity.ok(calendars);
    }

    @GetMapping("/vaccine/{vaccineId}")
    public ResponseEntity<?> getCalendarsByVaccine(
            @PathVariable Long vaccineId,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<VaccinationCalendar> calendars;
            
            if (startDate != null && endDate != null) {
                // Lấy lịch tiêm trong khoảng thời gian
                calendars = calendarService.getCalendarsByVaccineAndDateRange(vaccineId, startDate, endDate);
            } else if (isActive != null) {
                // Lấy lịch tiêm theo trạng thái
                calendars = calendarService.getCalendarsByVaccineAndStatus(vaccineId, isActive);
            } else {
                // Lấy tất cả lịch tiêm của vaccine
                calendars = calendarService.getCalendarsByVaccine(vaccineId);
            }
            
            if (calendars.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Không tìm thấy lịch tiêm cho vaccine này");
                return ResponseEntity.ok(response);
            }
            
            return ResponseEntity.ok(calendars);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Lỗi khi lấy danh sách lịch tiêm");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCalendar(@RequestBody VaccinationCalendar calendar) {
        try {
            VaccinationCalendar createdCalendar = calendarService.createCalendar(calendar);
            return ResponseEntity.ok(createdCalendar);
        } catch (VaccinationCalendarException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Lỗi lịch tiêm vaccine");
            response.put("message", e.getMessage());
            return ResponseEntity.status(409).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Lỗi hệ thống");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCalendar(
            @PathVariable Long id,
            @RequestBody VaccinationCalendar calendarDetails) {
        try {
            VaccinationCalendar updatedCalendar = calendarService.updateCalendar(id, calendarDetails);
            return ResponseEntity.ok(updatedCalendar);
        } catch (VaccinationCalendarException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Lỗi lịch tiêm vaccine");
            response.put("message", e.getMessage());
            return ResponseEntity.status(409).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Lỗi hệ thống");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {
        try {
            calendarService.deleteCalendar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/increment")
    public ResponseEntity<Void> incrementCurrentPatients(@PathVariable Long id) {
        try {
            calendarService.incrementCurrentPatients(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/decrement")
    public ResponseEntity<Void> decrementCurrentPatients(@PathVariable Long id) {
        try {
            calendarService.decrementCurrentPatients(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
} 