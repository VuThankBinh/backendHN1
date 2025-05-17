package com.datn.backendHN.service;

import com.datn.backendHN.dto.request.CreateLeTanRequest;
import com.datn.backendHN.dto.request.UpdateLeTanRequest;
import com.datn.backendHN.dto.response.LeTanResponse;
import com.datn.backendHN.entity.LeTanEntity;
import com.datn.backendHN.entity.NguoiDungEntity;
import com.datn.backendHN.enums.VaiTro;
import com.datn.backendHN.repository.LeTanRepository;
import com.datn.backendHN.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeTanService {
    private final LeTanRepository leTanRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;

    public List<LeTanResponse> getAllLeTan() {
        return leTanRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public LeTanResponse getLeTanById(Integer id) {
        return leTanRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lễ tân"));
    }

    @Transactional
    public LeTanResponse createLeTan(CreateLeTanRequest request) {
        // Kiểm tra email đã tồn tại
        if (nguoiDungRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Kiểm tra CCCD đã tồn tại
        if (request.getCccd() != null && nguoiDungRepository.existsByCccd(request.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        // Tạo người dùng mới
        NguoiDungEntity nguoiDung = NguoiDungEntity.builder()
                .email(request.getEmail())
                .matKhau(passwordEncoder.encode(request.getMatKhau()))
                .hoTen(request.getHoTen())
                .soDienThoai(request.getSoDienThoai())
                .diaChi(request.getDiaChi())
                .ngaySinh(request.getNgaySinh() != null ? LocalDate.parse(request.getNgaySinh()) : null)
                .gioiTinh(request.getGioiTinh())
                .cccd(request.getCccd())
                .vaiTro(VaiTro.LE_TAN.toString())
                .daKichHoat(true)
                .build();

        nguoiDung = nguoiDungRepository.save(nguoiDung);

        // Tạo lễ tân mới
        LeTanEntity leTan = LeTanEntity.builder()
                .nguoiDung(nguoiDung)
                .caLamViec(LeTanEntity.CaLamViec.valueOf(request.getCaLamViec()))
                .build();

        return convertToResponse(leTanRepository.save(leTan));
    }

    @Transactional
    public LeTanResponse updateLeTan(Integer id, UpdateLeTanRequest request) {
        LeTanEntity leTan = leTanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lễ tân"));

        NguoiDungEntity nguoiDung = leTan.getNguoiDung();

        // Kiểm tra CCCD đã tồn tại (nếu có thay đổi)
        if (request.getCccd() != null && !request.getCccd().equals(nguoiDung.getCccd()) &&
                nguoiDungRepository.existsByCccd(request.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        // Cập nhật thông tin người dùng
        nguoiDung.setHoTen(request.getHoTen());
        nguoiDung.setSoDienThoai(request.getSoDienThoai());
        nguoiDung.setDiaChi(request.getDiaChi());
        nguoiDung.setNgaySinh(request.getNgaySinh() != null ? LocalDate.parse(request.getNgaySinh()) : null);
        nguoiDung.setGioiTinh(request.getGioiTinh());
        nguoiDung.setCccd(request.getCccd());

        nguoiDungRepository.save(nguoiDung);

        // Cập nhật thông tin lễ tân
        leTan.setCaLamViec(LeTanEntity.CaLamViec.valueOf(request.getCaLamViec()));

        return convertToResponse(leTanRepository.save(leTan));
    }

    @Transactional
    public void deleteLeTan(Integer id) {
        LeTanEntity leTan = leTanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lễ tân"));

        // Xóa lễ tân sẽ tự động xóa người dùng (do có CASCADE)
        leTanRepository.delete(leTan);
    }

    private LeTanResponse convertToResponse(LeTanEntity leTan) {
        NguoiDungEntity nguoiDung = leTan.getNguoiDung();
        return LeTanResponse.builder()
                .idLeTan(leTan.getIdLeTan())
                .idNguoiDung(nguoiDung.getIdNguoiDung())
                .email(nguoiDung.getEmail())
                .hoTen(nguoiDung.getHoTen())
                .soDienThoai(nguoiDung.getSoDienThoai())
                .diaChi(nguoiDung.getDiaChi())
                .ngaySinh(nguoiDung.getNgaySinh() != null ? nguoiDung.getNgaySinh().toString() : null)
                .gioiTinh(nguoiDung.getGioiTinh())
                .cccd(nguoiDung.getCccd())
                .caLamViec(leTan.getCaLamViec().name())
                .daKichHoat(nguoiDung.getDaKichHoat())
                .build();
    }
} 