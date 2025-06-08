package com.datn.backendHN.controller;

import com.datn.backendHN.entity.Vaccine;
import com.datn.backendHN.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
@CrossOrigin(origins = "*")
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;

    @GetMapping
    public List<Vaccine> getAllVaccines() {
        return vaccineService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccineById(@PathVariable Long id) {
        return vaccineService.getVaccineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vaccine>> searchVaccines(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Boolean isActive) {
        List<Vaccine> vaccines = vaccineService.searchVaccines(name, manufacturer, isActive);
        return ResponseEntity.ok(vaccines);
    }

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody Vaccine vaccine) {
        try {
            Vaccine createdVaccine = vaccineService.createVaccine(vaccine);
            return ResponseEntity.ok(createdVaccine);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody Vaccine vaccine) {
        try {
            Vaccine updatedVaccine = vaccineService.updateVaccine(id, vaccine);
            return ResponseEntity.ok(updatedVaccine);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        try {
            vaccineService.deleteVaccine(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 