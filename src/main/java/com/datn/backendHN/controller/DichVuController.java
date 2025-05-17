package com.datn.backendHN.controller;

import com.datn.backendHN.dto.DichVuDTO;
import com.datn.backendHN.service.DichVuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dich-vu")
@RequiredArgsConstructor
public class DichVuController {
    private final DichVuService dichVuService;

    @GetMapping
    public ResponseEntity<List<DichVuDTO>> getAllDichVu() {
        return ResponseEntity.ok(dichVuService.getAllDichVu());
    }

    @GetMapping("/chuyen-khoa/{chuyenKhoaId}")
    public ResponseEntity<List<DichVuDTO>> getDichVuByChuyenKhoaId(@PathVariable Integer chuyenKhoaId) {
        return ResponseEntity.ok(dichVuService.getDichVuByChuyenKhoaId(chuyenKhoaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DichVuDTO> getDichVuById(@PathVariable Integer id) {
        return ResponseEntity.ok(dichVuService.getDichVuById(id));
    }

    @PostMapping
    public ResponseEntity<DichVuDTO> createDichVu(@RequestBody DichVuDTO dichVuDTO) {
        return ResponseEntity.ok(dichVuService.createDichVu(dichVuDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DichVuDTO> updateDichVu(@PathVariable Integer id, @RequestBody DichVuDTO dichVuDTO) {
        return ResponseEntity.ok(dichVuService.updateDichVu(id, dichVuDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDichVu(@PathVariable Integer id) {
        dichVuService.deleteDichVu(id);
        return ResponseEntity.ok().build();
    }
} 