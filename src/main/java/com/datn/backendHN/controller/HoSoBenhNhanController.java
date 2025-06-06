package com.datn.backendHN.controller;

import com.datn.backendHN.entity.HoSoBenhNhan;
import com.datn.backendHN.service.HoSoBenhNhanService;
import com.datn.backendHN.exception.DuplicateResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoso-benh-nhan")
@CrossOrigin(origins = "*")
public class HoSoBenhNhanController {

    @Autowired
    private HoSoBenhNhanService hoSoBenhNhanService;

    @GetMapping
    public ResponseEntity<List<HoSoBenhNhan>> getAllHoSoBenhNhan() {
        return ResponseEntity.ok(hoSoBenhNhanService.getAllHoSoBenhNhan());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoSoBenhNhan> getHoSoBenhNhanById(@PathVariable Integer id) {
        return hoSoBenhNhanService.getHoSoBenhNhanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nguoi-dung/{nguoiDungId}")
    public ResponseEntity<List<HoSoBenhNhan>> getHoSoBenhNhanByNguoiDungId(@PathVariable Integer nguoiDungId) {
        return ResponseEntity.ok(hoSoBenhNhanService.getHoSoBenhNhanByNguoiDungId(nguoiDungId));
    }

    @PostMapping
    public ResponseEntity<?> createHoSoBenhNhan(@RequestBody HoSoBenhNhan hoSoBenhNhan) {
        try {
            HoSoBenhNhan created = hoSoBenhNhanService.createHoSoBenhNhan(hoSoBenhNhan);
            return ResponseEntity.ok(created);
        } catch (DuplicateResourceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHoSoBenhNhan(@PathVariable Integer id, @RequestBody HoSoBenhNhan hoSoBenhNhan) {
        try {
            HoSoBenhNhan updated = hoSoBenhNhanService.updateHoSoBenhNhan(id, hoSoBenhNhan);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.notFound().build();
        } catch (DuplicateResourceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoSoBenhNhan(@PathVariable Integer id) {
        hoSoBenhNhanService.deleteHoSoBenhNhan(id);
        return ResponseEntity.ok().build();
    }
} 