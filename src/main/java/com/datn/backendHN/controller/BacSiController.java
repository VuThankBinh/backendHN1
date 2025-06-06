package com.datn.backendHN.controller;

import com.datn.backendHN.dto.request.CreateBacSiRequest;
import com.datn.backendHN.dto.request.UpdateBacSiRequest;
import com.datn.backendHN.dto.response.BacSiResponse;
import com.datn.backendHN.service.BacSiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bac-si")
@RequiredArgsConstructor
public class BacSiController {
    private final BacSiService bacSiService;

    @GetMapping
    public ResponseEntity<List<BacSiResponse>> getAllBacSi() {
        return ResponseEntity.ok(bacSiService.getAllBacSi());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BacSiResponse> getBacSiById(@PathVariable Integer id) {
        return ResponseEntity.ok(bacSiService.getBacSiById(id));
    }

    @PostMapping
    public ResponseEntity<BacSiResponse> createBacSi(@RequestBody CreateBacSiRequest request) {
        return ResponseEntity.ok(bacSiService.createBacSi(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BacSiResponse> updateBacSi(@PathVariable Integer id, @RequestBody UpdateBacSiRequest request) {
        return ResponseEntity.ok(bacSiService.updateBacSi(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBacSi(@PathVariable Integer id) {
        bacSiService.deleteBacSi(id);
        return ResponseEntity.ok().build();
    }
} 