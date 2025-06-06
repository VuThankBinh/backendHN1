package com.datn.backendHN.controller;

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

    @GetMapping
    public List<GoiKham> getAllGoiKham() {
        return goiKhamService.getAllGoiKham();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoiKham> getGoiKhamById(@PathVariable Integer id) {
        return goiKhamService.getGoiKhamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public GoiKham createGoiKham(@RequestBody GoiKham goiKham) {
        return goiKhamService.createGoiKham(goiKham);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoiKham> updateGoiKham(@PathVariable Integer id, @RequestBody GoiKham goiKhamDetails) {
        try {
            GoiKham updatedGoiKham = goiKhamService.updateGoiKham(id, goiKhamDetails);
            return ResponseEntity.ok(updatedGoiKham);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoiKham(@PathVariable Integer id) {
        try {
            goiKhamService.deleteGoiKham(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 