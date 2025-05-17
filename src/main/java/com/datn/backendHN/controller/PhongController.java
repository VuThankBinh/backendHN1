package com.datn.backendHN.controller;

import com.datn.backendHN.dto.PhongDTO;
import com.datn.backendHN.service.PhongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phong")
@RequiredArgsConstructor
public class PhongController {
    private final PhongService phongService;

    @GetMapping
    public ResponseEntity<List<PhongDTO>> getAllPhong() {
        return ResponseEntity.ok(phongService.getAllPhong());
    }

    @GetMapping("/khoa/{khoaId}")
    public ResponseEntity<List<PhongDTO>> getPhongByKhoaId(@PathVariable Integer khoaId) {
        return ResponseEntity.ok(phongService.getPhongByKhoaId(khoaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhongDTO> getPhongById(@PathVariable Integer id) {
        return ResponseEntity.ok(phongService.getPhongById(id));
    }

    @PostMapping
    public ResponseEntity<PhongDTO> createPhong(@RequestBody PhongDTO phongDTO) {
        return ResponseEntity.ok(phongService.createPhong(phongDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhongDTO> updatePhong(@PathVariable Integer id, @RequestBody PhongDTO phongDTO) {
        return ResponseEntity.ok(phongService.updatePhong(id, phongDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhong(@PathVariable Integer id) {
        phongService.deletePhong(id);
        return ResponseEntity.ok().build();
    }
} 