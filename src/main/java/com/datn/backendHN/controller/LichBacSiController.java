package com.datn.backendHN.controller;

import com.datn.backendHN.dto.request.CreateLichBacSiRequest;
import com.datn.backendHN.dto.request.UpdateLichBacSiRequest;
import com.datn.backendHN.dto.response.LichBacSiResponse;
import com.datn.backendHN.service.LichBacSiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lich-bac-si")
@RequiredArgsConstructor
public class LichBacSiController {
    private final LichBacSiService lichBacSiService;

    @PostMapping
    public ResponseEntity<LichBacSiResponse> createLichBacSi(@RequestBody CreateLichBacSiRequest request) {
        return ResponseEntity.ok(lichBacSiService.createLichBacSi(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LichBacSiResponse> updateLichBacSi(
            @PathVariable Integer id,
            @RequestBody UpdateLichBacSiRequest request) {
        return ResponseEntity.ok(lichBacSiService.updateLichBacSi(id, request));
    }

    @GetMapping("/bac-si/{idBacSi}")
    public ResponseEntity<List<LichBacSiResponse>> getLichBacSiByBacSiId(@PathVariable Integer idBacSi) {
        return ResponseEntity.ok(lichBacSiService.getLichBacSiByBacSiId(idBacSi));
    }

    @GetMapping("/bac-si/{idBacSi}/dang-kha-dung/{dangKhaDung}")
    public ResponseEntity<List<LichBacSiResponse>> getLichBacSiByBacSiIdAndDangKhaDung(
            @PathVariable Integer idBacSi,
            @PathVariable Boolean dangKhaDung) {
        return ResponseEntity.ok(lichBacSiService.getLichBacSiByBacSiIdAndDangKhaDung(idBacSi, dangKhaDung));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLichBacSi(@PathVariable Integer id) {
        lichBacSiService.deleteLichBacSi(id);
        return ResponseEntity.ok().build();
    }
} 