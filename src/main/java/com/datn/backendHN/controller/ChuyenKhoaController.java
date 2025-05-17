package com.datn.backendHN.controller;

import com.datn.backendHN.dto.ChuyenKhoaDTO;
import com.datn.backendHN.service.ChuyenKhoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chuyen-khoa")
@RequiredArgsConstructor
public class ChuyenKhoaController {
    private final ChuyenKhoaService chuyenKhoaService;

    @GetMapping
    public ResponseEntity<List<ChuyenKhoaDTO>> getAllChuyenKhoa() {
        return ResponseEntity.ok(chuyenKhoaService.getAllChuyenKhoa());
    }

    @GetMapping("/khoa/{khoaId}")
    public ResponseEntity<List<ChuyenKhoaDTO>> getChuyenKhoaByKhoaId(@PathVariable Integer khoaId) {
        return ResponseEntity.ok(chuyenKhoaService.getChuyenKhoaByKhoaId(khoaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChuyenKhoaDTO> getChuyenKhoaById(@PathVariable Integer id) {
        return ResponseEntity.ok(chuyenKhoaService.getChuyenKhoaById(id));
    }

    @PostMapping
    public ResponseEntity<ChuyenKhoaDTO> createChuyenKhoa(@RequestBody ChuyenKhoaDTO chuyenKhoaDTO) {
        return ResponseEntity.ok(chuyenKhoaService.createChuyenKhoa(chuyenKhoaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChuyenKhoaDTO> updateChuyenKhoa(@PathVariable Integer id, @RequestBody ChuyenKhoaDTO chuyenKhoaDTO) {
        return ResponseEntity.ok(chuyenKhoaService.updateChuyenKhoa(id, chuyenKhoaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChuyenKhoa(@PathVariable Integer id) {
        chuyenKhoaService.deleteChuyenKhoa(id);
        return ResponseEntity.ok().build();
    }
} 