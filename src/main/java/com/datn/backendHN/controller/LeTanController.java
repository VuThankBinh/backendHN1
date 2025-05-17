package com.datn.backendHN.controller;

import com.datn.backendHN.dto.request.CreateLeTanRequest;
import com.datn.backendHN.dto.request.UpdateLeTanRequest;
import com.datn.backendHN.dto.response.LeTanResponse;
import com.datn.backendHN.service.LeTanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/le-tan")
@RequiredArgsConstructor
public class LeTanController {
    private final LeTanService leTanService;

    @GetMapping
    public ResponseEntity<List<LeTanResponse>> getAllLeTan() {
        return ResponseEntity.ok(leTanService.getAllLeTan());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeTanResponse> getLeTanById(@PathVariable Integer id) {
        return ResponseEntity.ok(leTanService.getLeTanById(id));
    }

    @PostMapping
    public ResponseEntity<LeTanResponse> createLeTan(@RequestBody CreateLeTanRequest request) {
        return ResponseEntity.ok(leTanService.createLeTan(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeTanResponse> updateLeTan(@PathVariable Integer id, @RequestBody UpdateLeTanRequest request) {
        return ResponseEntity.ok(leTanService.updateLeTan(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeTan(@PathVariable Integer id) {
        leTanService.deleteLeTan(id);
        return ResponseEntity.ok().build();
    }
} 