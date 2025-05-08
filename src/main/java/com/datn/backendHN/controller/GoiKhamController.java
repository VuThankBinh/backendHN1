package com.datn.backendHN.controller;

import com.datn.backendHN.dto.GoiKhamDTO;
import com.datn.backendHN.entity.GoiKham;
import com.datn.backendHN.service.GoiKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goi-kham")
@CrossOrigin(origins = "*")
public class GoiKhamController {

    @Autowired
    private GoiKhamService goiKhamService;

    @PostMapping
    public ResponseEntity<GoiKham> createGoiKham(@RequestBody GoiKhamDTO goiKhamDTO) {
        return ResponseEntity.ok(goiKhamService.createGoiKham(goiKhamDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoiKham> updateGoiKham(@PathVariable Integer id, @RequestBody GoiKhamDTO goiKhamDTO) {
        return ResponseEntity.ok(goiKhamService.updateGoiKham(id, goiKhamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoiKham(@PathVariable Integer id) {
        goiKhamService.deleteGoiKham(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoiKham> getGoiKhamById(@PathVariable Integer id) {
        return ResponseEntity.ok(goiKhamService.getGoiKhamById(id));
    }

    @GetMapping
    public ResponseEntity<List<GoiKham>> getAllGoiKham() {
        return ResponseEntity.ok(goiKhamService.getAllGoiKham());
    }

    @GetMapping("/chuyen-khoa/{chuyenKhoaId}")
    public ResponseEntity<List<GoiKham>> getGoiKhamByChuyenKhoa(@PathVariable Integer chuyenKhoaId) {
        return ResponseEntity.ok(goiKhamService.getGoiKhamByChuyenKhoa(chuyenKhoaId));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<GoiKham>> getGoiKhamByTrangThai(@PathVariable String trangThai) {
        return ResponseEntity.ok(goiKhamService.getGoiKhamByTrangThai(trangThai));
    }
} 