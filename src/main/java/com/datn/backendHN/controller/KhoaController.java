package com.datn.backendHN.controller;

import com.datn.backendHN.dto.KhoaDTO;
import com.datn.backendHN.service.KhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khoa")
@CrossOrigin(origins = "*")
public class KhoaController {

    @Autowired
    private KhoaService khoaService;

    @GetMapping
    public ResponseEntity<List<KhoaDTO>> getAllKhoa() {
        return ResponseEntity.ok(khoaService.getAllKhoa());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhoaDTO> getKhoaById(@PathVariable Integer id) {
        return ResponseEntity.ok(khoaService.getKhoaById(id));
    }

    @PostMapping
    public ResponseEntity<KhoaDTO> createKhoa(@RequestBody KhoaDTO khoaDTO) {
        return ResponseEntity.ok(khoaService.createKhoa(khoaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhoaDTO> updateKhoa(@PathVariable Integer id, @RequestBody KhoaDTO khoaDTO) {
        return ResponseEntity.ok(khoaService.updateKhoa(id, khoaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhoa(@PathVariable Integer id) {
        khoaService.deleteKhoa(id);
        return ResponseEntity.ok().build();
    }
} 