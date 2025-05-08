package com.datn.backendHN.controller;

import com.datn.backendHN.dto.DichVuDTO;
import com.datn.backendHN.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dich-vu")
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;

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

    @GetMapping("/{id}")
    public ResponseEntity<DichVuDTO> getDichVuById(@PathVariable Integer id) {
        return ResponseEntity.ok(dichVuService.getDichVuById(id));
    }

    @GetMapping
    public ResponseEntity<List<DichVuDTO>> getAllDichVu() {
        return ResponseEntity.ok(dichVuService.getAllDichVu());
    }

    @GetMapping("/loai/{loaiDichVu}")
    public ResponseEntity<List<DichVuDTO>> getDichVuByLoai(@PathVariable String loaiDichVu) {
        return ResponseEntity.ok(dichVuService.getDichVuByLoai(loaiDichVu));
    }

    @GetMapping("/trang-thai/{trangThai}")
    public ResponseEntity<List<DichVuDTO>> getDichVuByTrangThai(@PathVariable String trangThai) {
        return ResponseEntity.ok(dichVuService.getDichVuByTrangThai(trangThai));
    }
} 