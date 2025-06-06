package com.datn.backendHN.controller;

import com.datn.backendHN.entity.DangKyGoiKham;
import com.datn.backendHN.enums.TrangThaiDangKy;
import com.datn.backendHN.service.DangKyGoiKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dang-ky-goi-kham")
@CrossOrigin(origins = "*")
public class DangKyGoiKhamController {

    @Autowired
    private DangKyGoiKhamService dangKyGoiKhamService;

    @PostMapping
    public ResponseEntity<?> createDangKyGoiKham(@RequestBody DangKyGoiKham dangKyGoiKham) {
        try {
            DangKyGoiKham created = dangKyGoiKhamService.createDangKyGoiKham(dangKyGoiKham);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{maDangKy}")
    public ResponseEntity<DangKyGoiKham> updateDangKyGoiKham(
            @PathVariable Integer maDangKy,
            @RequestBody DangKyGoiKham dangKyGoiKham) {
        return ResponseEntity.ok(dangKyGoiKhamService.updateDangKyGoiKham(maDangKy, dangKyGoiKham));
    }

    @PatchMapping("/{maDangKy}/trang-thai")
    public ResponseEntity<?> updateTrangThai(
            @PathVariable Integer maDangKy,
            @RequestParam TrangThaiDangKy trangThai) {
        try {
            DangKyGoiKham updated = dangKyGoiKhamService.updateTrangThai(maDangKy, trangThai);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{maDangKy}")
    public ResponseEntity<Void> deleteDangKyGoiKham(@PathVariable Integer maDangKy) {
        dangKyGoiKhamService.deleteDangKyGoiKham(maDangKy);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{maDangKy}")
    public ResponseEntity<DangKyGoiKham> getDangKyGoiKhamById(@PathVariable Integer maDangKy) {
        return ResponseEntity.ok(dangKyGoiKhamService.getDangKyGoiKhamById(maDangKy));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DangKyGoiKham>> getDangKyGoiKhamByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(dangKyGoiKhamService.getDangKyGoiKhamByUserId(userId));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<DangKyGoiKham>> getDangKyGoiKhamByTrangThai(@PathVariable TrangThaiDangKy trangThai) {
        return ResponseEntity.ok(dangKyGoiKhamService.getDangKyGoiKhamByTrangThai(trangThai));
    }

    @GetMapping("/user/{userId}/trang-thai/{trangThai}")
    public ResponseEntity<List<DangKyGoiKham>> getDangKyGoiKhamByUserIdAndTrangThai(
            @PathVariable Integer userId,
            @PathVariable TrangThaiDangKy trangThai) {
        return ResponseEntity.ok(dangKyGoiKhamService.getDangKyGoiKhamByUserIdAndTrangThai(userId, trangThai));
    }

    @GetMapping
    public ResponseEntity<List<DangKyGoiKham>> getAllDangKyGoiKham() {
        return ResponseEntity.ok(dangKyGoiKhamService.getAllDangKyGoiKham());
    }
} 