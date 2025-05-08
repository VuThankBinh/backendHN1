package com.datn.backendHN.controller;

import com.datn.backendHN.dto.ThuocDTO;
import com.datn.backendHN.entity.Thuoc;
import com.datn.backendHN.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thuoc")
@CrossOrigin(origins = "*")
public class ThuocController {

    @Autowired
    private ThuocService thuocService;

    @PostMapping
    public ResponseEntity<Thuoc> createThuoc(@RequestBody ThuocDTO thuocDTO) {
        return ResponseEntity.ok(thuocService.createThuoc(thuocDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thuoc> updateThuoc(@PathVariable Integer id, @RequestBody ThuocDTO thuocDTO) {
        return ResponseEntity.ok(thuocService.updateThuoc(id, thuocDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThuoc(@PathVariable Integer id) {
        thuocService.deleteThuoc(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thuoc> getThuocById(@PathVariable Integer id) {
        return ResponseEntity.ok(thuocService.getThuocById(id));
    }

    @GetMapping
    public ResponseEntity<List<Thuoc>> getAllThuoc() {
        return ResponseEntity.ok(thuocService.getAllThuoc());
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<Thuoc>> getThuocByTrangThai(@PathVariable String trangThai) {
        return ResponseEntity.ok(thuocService.getThuocByTrangThai(trangThai));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Thuoc>> searchThuoc(@RequestParam String keyword) {
        return ResponseEntity.ok(thuocService.searchThuoc(keyword));
    }

    @PutMapping("/{id}/so-luong")
    public ResponseEntity<Void> updateSoLuong(@PathVariable Integer id, @RequestParam Integer soLuong) {
        thuocService.updateSoLuong(id, soLuong);
        return ResponseEntity.ok().build();
    }
} 