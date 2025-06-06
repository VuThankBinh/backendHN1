package com.datn.backendHN.service;

import com.datn.backendHN.dto.request.CreateLichBacSiRequest;
import com.datn.backendHN.dto.request.UpdateLichBacSiRequest;
import com.datn.backendHN.dto.response.LichBacSiResponse;
import com.datn.backendHN.entity.BacSiEntity;
import com.datn.backendHN.entity.LichBacSiEntity;
import com.datn.backendHN.repository.BacSiRepository;
import com.datn.backendHN.repository.LichBacSiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LichBacSiService {
    private final LichBacSiRepository lichBacSiRepository;
    private final BacSiRepository bacSiRepository;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Transactional
    public LichBacSiResponse createLichBacSi(CreateLichBacSiRequest request) {
        BacSiEntity bacSi = bacSiRepository.findById(request.getIdBacSi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));

        LichBacSiEntity lichBacSi = LichBacSiEntity.builder()
                .bacSi(bacSi)
                .thu(request.getThu())
                .gioBatDau(LocalTime.parse(request.getGioBatDau(), TIME_FORMATTER))
                .gioKetThuc(LocalTime.parse(request.getGioKetThuc(), TIME_FORMATTER))
                .dangKhaDung(request.getDangKhaDung())
                .laLichLap(request.getLaLichLap())
                .cacNgayLap(request.getCacNgayLap())
                .ngayBatDau(LocalDate.parse(request.getNgayBatDau(), DATE_FORMATTER))
                .ngayKetThuc(LocalDate.parse(request.getNgayKetThuc(), DATE_FORMATTER))
                .build();

        LichBacSiEntity savedLichBacSi = lichBacSiRepository.save(lichBacSi);
        return convertToResponse(savedLichBacSi);
    }

    @Transactional
    public LichBacSiResponse updateLichBacSi(Integer id, UpdateLichBacSiRequest request) {
        LichBacSiEntity lichBacSi = lichBacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch làm việc"));

        lichBacSi.setThu(request.getThu());
        lichBacSi.setGioBatDau(LocalTime.parse(request.getGioBatDau(), TIME_FORMATTER));
        lichBacSi.setGioKetThuc(LocalTime.parse(request.getGioKetThuc(), TIME_FORMATTER));
        lichBacSi.setDangKhaDung(request.getDangKhaDung());
        lichBacSi.setLaLichLap(request.getLaLichLap());
        lichBacSi.setCacNgayLap(request.getCacNgayLap());
        lichBacSi.setNgayBatDau(LocalDate.parse(request.getNgayBatDau(), DATE_FORMATTER));
        lichBacSi.setNgayKetThuc(LocalDate.parse(request.getNgayKetThuc(), DATE_FORMATTER));

        LichBacSiEntity updatedLichBacSi = lichBacSiRepository.save(lichBacSi);
        return convertToResponse(updatedLichBacSi);
    }

    public List<LichBacSiResponse> getLichBacSiByBacSiId(Integer idBacSi) {
        List<LichBacSiEntity> lichBacSiList = lichBacSiRepository.findByBacSiId(idBacSi);
        return lichBacSiList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<LichBacSiResponse> getLichBacSiByBacSiIdAndDangKhaDung(Integer idBacSi, Boolean dangKhaDung) {
        List<LichBacSiEntity> lichBacSiList = lichBacSiRepository.findByBacSiIdAndDangKhaDung(idBacSi, dangKhaDung);
        return lichBacSiList.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLichBacSi(Integer id) {
        lichBacSiRepository.deleteById(id);
    }

    private LichBacSiResponse convertToResponse(LichBacSiEntity entity) {
        return LichBacSiResponse.builder()
                .idLichBacSi(entity.getIdLichBacSi())
                .idBacSi(entity.getBacSi().getId())
                .tenBacSi(entity.getBacSi().getNguoiDung().getHoTen())
                .thu(entity.getThu())
                .gioBatDau(entity.getGioBatDau().format(TIME_FORMATTER))
                .gioKetThuc(entity.getGioKetThuc().format(TIME_FORMATTER))
                .dangKhaDung(entity.getDangKhaDung())
                .laLichLap(entity.getLaLichLap())
                .cacNgayLap(entity.getCacNgayLap())
                .ngayBatDau(entity.getNgayBatDau().format(DATE_FORMATTER))
                .ngayKetThuc(entity.getNgayKetThuc().format(DATE_FORMATTER))
                .build();
    }
} 