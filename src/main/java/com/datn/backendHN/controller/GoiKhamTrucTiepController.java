package com.datn.backendHN.controller;

import com.datn.backendHN.dto.request.CreateGoiKhamTrucTiepRequest;
import com.datn.backendHN.dto.response.GoiKhamTrucTiepResponse;
import com.datn.backendHN.service.GoiKhamTrucTiepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goi-kham-truc-tiep")
@RequiredArgsConstructor
public class GoiKhamTrucTiepController {
    private final GoiKhamTrucTiepService goiKhamTrucTiepService;

    @GetMapping
    public ResponseEntity<List<GoiKhamTrucTiepResponse>> getAllGoiKham() {
        return ResponseEntity.ok(goiKhamTrucTiepService.getAllGoiKham());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoiKhamTrucTiepResponse> getGoiKhamById(@PathVariable Integer id) {
        return ResponseEntity.ok(goiKhamTrucTiepService.getGoiKhamById(id));
    }

    @PostMapping
    public ResponseEntity<GoiKhamTrucTiepResponse> createGoiKham(@RequestBody CreateGoiKhamTrucTiepRequest request) {
        return ResponseEntity.ok(goiKhamTrucTiepService.createGoiKham(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GoiKhamTrucTiepResponse>> searchGoiKham(
            @RequestParam(required = false) String tenGoiKham,
            @RequestParam(required = false) Integer idChuyenKhoa,
            @RequestParam(required = false) Boolean trangThai) {
        return ResponseEntity.ok(goiKhamTrucTiepService.searchGoiKham(tenGoiKham, idChuyenKhoa, trangThai));
    }

    @GetMapping("/chuyen-khoa/{idChuyenKhoa}")
    public ResponseEntity<List<GoiKhamTrucTiepResponse>> getGoiKhamByChuyenKhoa(@PathVariable Integer idChuyenKhoa) {
        return ResponseEntity.ok(goiKhamTrucTiepService.getGoiKhamByChuyenKhoa(idChuyenKhoa));
    }
} 