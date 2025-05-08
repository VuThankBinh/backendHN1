package com.datn.backendHN.controller;

import com.datn.backendHN.dto.ChucVuDTO;
import com.datn.backendHN.entity.ChucVu;
import com.datn.backendHN.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chuc-vu")
@CrossOrigin(origins = "*")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @PostMapping
    public ResponseEntity<ChucVu> createChucVu(@RequestBody ChucVuDTO chucVuDTO) {
        return ResponseEntity.ok(chucVuService.createChucVu(chucVuDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChucVu> updateChucVu(@PathVariable Integer id, @RequestBody ChucVuDTO chucVuDTO) {
        return ResponseEntity.ok(chucVuService.updateChucVu(id, chucVuDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChucVu(@PathVariable Integer id) {
        chucVuService.deleteChucVu(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChucVu> getChucVuById(@PathVariable Integer id) {
        return ResponseEntity.ok(chucVuService.getChucVuById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChucVu>> getAllChucVu() {
        return ResponseEntity.ok(chucVuService.getAllChucVu());
    }
} 