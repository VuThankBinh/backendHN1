package com.datn.backendHN.controller;

import com.datn.backendHN.dto.BacSiDTO;
import com.datn.backendHN.entity.BacSi;
import com.datn.backendHN.service.BacSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bac-si")
@CrossOrigin(origins = "*")
public class BacSiController {

    @Autowired
    private BacSiService bacSiService;

    @PostMapping
    public ResponseEntity<BacSi> createBacSi(@RequestBody BacSiDTO bacSiDTO) {
        return ResponseEntity.ok(bacSiService.createBacSi(bacSiDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BacSi> updateBacSi(@PathVariable Integer id, @RequestBody BacSiDTO bacSiDTO) {
        return ResponseEntity.ok(bacSiService.updateBacSi(id, bacSiDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBacSi(@PathVariable Integer id) {
        bacSiService.deleteBacSi(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BacSi> getBacSiById(@PathVariable Integer id) {
        return ResponseEntity.ok(bacSiService.getBacSiById(id));
    }

    @GetMapping
    public ResponseEntity<List<BacSi>> getAllBacSi() {
        return ResponseEntity.ok(bacSiService.getAllBacSi());
    }

    @GetMapping("/chuyen-khoa/{chuyenKhoaId}")
    public ResponseEntity<List<BacSi>> getBacSiByChuyenKhoa(@PathVariable Integer chuyenKhoaId) {
        return ResponseEntity.ok(bacSiService.getBacSiByChuyenKhoa(chuyenKhoaId));
    }

    @GetMapping("/phong-kham/{phongKhamId}")
    public ResponseEntity<List<BacSi>> getBacSiByPhongKham(@PathVariable Integer phongKhamId) {
        return ResponseEntity.ok(bacSiService.getBacSiByPhongKham(phongKhamId));
    }
} 