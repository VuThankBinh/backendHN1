package com.datn.backendHN.controller;

import com.datn.backendHN.dto.LichTiemDTO;
import com.datn.backendHN.service.LichTiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lich-tiem")
public class LichTiemController {

    @Autowired
    private LichTiemService lichTiemService;

    @PostMapping
    public ResponseEntity<LichTiemDTO> createLichTiem(@RequestBody LichTiemDTO lichTiemDTO) {
        return ResponseEntity.ok(lichTiemService.createLichTiem(lichTiemDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LichTiemDTO> updateLichTiem(@PathVariable Integer id, @RequestBody LichTiemDTO lichTiemDTO) {
        return ResponseEntity.ok(lichTiemService.updateLichTiem(id, lichTiemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLichTiem(@PathVariable Integer id) {
        lichTiemService.deleteLichTiem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LichTiemDTO> getLichTiemById(@PathVariable Integer id) {
        return ResponseEntity.ok(lichTiemService.getLichTiemById(id));
    }

    // @GetMapping("/benh-nhan/{benhNhanId}")
    // public ResponseEntity<List<LichTiemDTO>> getLichTiemByBenhNhanId(@PathVariable Integer benhNhanId) {
    //     return ResponseEntity.ok(lichTiemService.getLichTiemByBenhNhanId(benhNhanId));
    // }

    @GetMapping("/bac-si/{bacSiId}")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByBacSiId(@PathVariable Integer bacSiId) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByBacSiId(bacSiId));
    }

    // @GetMapping("/benh-nhan/{benhNhanId}/ngay/{ngay}")
    // public ResponseEntity<List<LichTiemDTO>> getLichTiemByBenhNhanIdAndNgay(
    //         @PathVariable Integer benhNhanId,
    //         @PathVariable LocalDate ngay) {
    //     return ResponseEntity.ok(lichTiemService.getLichTiemByBenhNhanIdAndNgay(benhNhanId, ngay));
    // }

    @GetMapping("/bac-si/{bacSiId}/ngay/{ngay}")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByBacSiIdAndNgay(
            @PathVariable Integer bacSiId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngay) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByBacSiIdAndNgay(bacSiId, ngay));
    }

    @GetMapping("/ngay/{ngay}")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByNgay(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngay) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByNgay(ngay));
    }

    @GetMapping("/khoang-thoi-gian")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByKhoangThoiGian(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByKhoangThoiGian(startDate, endDate));
    }

    @GetMapping("/bac-si/{bacSiId}/khoang-thoi-gian")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByBacSiIdAndKhoangThoiGian(
            @PathVariable Integer bacSiId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByBacSiIdAndKhoangThoiGian(bacSiId, startDate, endDate));
    }

    @GetMapping("/vaccine/{loaiVaccine}")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByLoaiVaccine(@PathVariable String loaiVaccine) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByLoaiVaccine(loaiVaccine));
    }

    @GetMapping("/vaccine/{loaiVaccine}/khoang-thoi-gian")
    public ResponseEntity<List<LichTiemDTO>> getLichTiemByLoaiVaccineAndKhoangThoiGian(
            @PathVariable String loaiVaccine,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(lichTiemService.getLichTiemByLoaiVaccineAndKhoangThoiGian(loaiVaccine, startDate, endDate));
    }

    @GetMapping("/bac-si-co-lich")
    public ResponseEntity<List<Integer>> getBacSiCoLichTrongKhoangThoiGian(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(lichTiemService.getBacSiCoLichTrongKhoangThoiGian(startDate, endDate));
    }
} 