package com.datn.backendHN.controller;

import com.datn.backendHN.dto.LichLamViecDTO;
import com.datn.backendHN.service.LichLamViecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lich-lam-viec")
public class LichLamViecController {

    @Autowired
    private LichLamViecService lichLamViecService;

    @PostMapping
    public ResponseEntity<LichLamViecDTO> createLichLamViec(@RequestBody LichLamViecDTO lichLamViecDTO) {
        return ResponseEntity.ok(lichLamViecService.createLichLamViec(lichLamViecDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LichLamViecDTO> updateLichLamViec(@PathVariable Integer id, @RequestBody LichLamViecDTO lichLamViecDTO) {
        return ResponseEntity.ok(lichLamViecService.updateLichLamViec(id, lichLamViecDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLichLamViec(@PathVariable Integer id) {
        lichLamViecService.deleteLichLamViec(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LichLamViecDTO> getLichLamViecById(@PathVariable Integer id) {
        return ResponseEntity.ok(lichLamViecService.getLichLamViecById(id));
    }

    @GetMapping("/bac-si/{bacSiId}")
    public ResponseEntity<List<LichLamViecDTO>> getLichLamViecByBacSiId(@PathVariable Integer bacSiId) {
        return ResponseEntity.ok(lichLamViecService.getLichLamViecByBacSiId(bacSiId));
    }

    @GetMapping("/bac-si/{bacSiId}/ngay/{ngay}")
    public ResponseEntity<List<LichLamViecDTO>> getLichLamViecByBacSiIdAndNgay(
            @PathVariable Integer bacSiId,
            @PathVariable LocalDate ngay) {
        return ResponseEntity.ok(lichLamViecService.getLichLamViecByBacSiIdAndNgay(bacSiId, ngay));
    }
} 