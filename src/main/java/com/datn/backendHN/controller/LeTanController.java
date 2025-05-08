package com.datn.backendHN.controller;

import com.datn.backendHN.dto.LeTanDTO;
import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.service.LeTanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/le-tan")
@CrossOrigin(origins = "*")
public class LeTanController {

    @Autowired
    private LeTanService leTanService;

    @PostMapping
    public ResponseEntity<ThanhVien> createLeTan(@RequestBody LeTanDTO leTanDTO) {
        return ResponseEntity.ok(leTanService.createLeTan(leTanDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThanhVien> updateLeTan(@PathVariable Integer id, @RequestBody LeTanDTO leTanDTO) {
        return ResponseEntity.ok(leTanService.updateLeTan(id, leTanDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeTan(@PathVariable Integer id) {
        leTanService.deleteLeTan(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThanhVien> getLeTanById(@PathVariable Integer id) {
        return ResponseEntity.ok(leTanService.getLeTanById(id));
    }

    @GetMapping
    public ResponseEntity<List<ThanhVien>> getAllLeTan() {
        return ResponseEntity.ok(leTanService.getAllLeTan());
    }
} 