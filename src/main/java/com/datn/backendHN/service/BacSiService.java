package com.datn.backendHN.service;

import com.datn.backendHN.dto.request.CreateBacSiRequest;
import com.datn.backendHN.dto.request.UpdateBacSiRequest;
import com.datn.backendHN.dto.response.BacSiResponse;
import com.datn.backendHN.entity.BacSiEntity;
import com.datn.backendHN.entity.ChuyenKhoaEntity;
import com.datn.backendHN.entity.KhoaEntity;
import com.datn.backendHN.entity.NguoiDungEntity;
import com.datn.backendHN.enums.VaiTro;
import com.datn.backendHN.repository.BacSiRepository;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.KhoaRepository;
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
public class BacSiService {
    private final BacSiRepository bacSiRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final ChuyenKhoaRepository chuyenKhoaRepository;
    private final KhoaRepository khoaRepository;
    private final PasswordEncoder passwordEncoder;

    public List<BacSiResponse> getAllBacSi() {
        return bacSiRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public BacSiResponse getBacSiById(Integer id) {
        return bacSiRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));
    }

    @Transactional
    public BacSiResponse createBacSi(CreateBacSiRequest request) {
        // Kiểm tra email đã tồn tại
        if (nguoiDungRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Kiểm tra CCCD đã tồn tại
        if (request.getCccd() != null && nguoiDungRepository.existsByCccd(request.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        // Kiểm tra số giấy phép đã tồn tại
        if (bacSiRepository.existsBySoGiayPhep(request.getSoGiayPhep())) {
            throw new RuntimeException("Số giấy phép đã tồn tại");
        }

        // Kiểm tra chuyên khoa và khoa tồn tại
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(request.getIdChuyenKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));
        
        KhoaEntity khoa = khoaRepository.findById(request.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

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
                .vaiTro(VaiTro.BAC_SI.toString())
                .daKichHoat(true)
                .build();

        nguoiDung = nguoiDungRepository.save(nguoiDung);

        // Tạo bác sĩ mới
        BacSiEntity bacSi = BacSiEntity.builder()
                .nguoiDung(nguoiDung)
                .chuyenKhoa(chuyenKhoa)
                .khoa(khoa)
                .chuyenMon(request.getChuyenMon())
                .soGiayPhep(request.getSoGiayPhep())
                .soNamKinhNghiem(request.getSoNamKinhNghiem())
                .dangLamViec(true)
                .anhDaiDien(request.getAnhDaiDien())
                .build();

        return convertToResponse(bacSiRepository.save(bacSi));
    }

    @Transactional
    public BacSiResponse updateBacSi(Integer id, UpdateBacSiRequest request) {
        BacSiEntity bacSi = bacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));

        NguoiDungEntity nguoiDung = bacSi.getNguoiDung();

        // Kiểm tra CCCD đã tồn tại (nếu có thay đổi)
        if (request.getCccd() != null && !request.getCccd().equals(nguoiDung.getCccd()) &&
                nguoiDungRepository.existsByCccd(request.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        // Kiểm tra số giấy phép đã tồn tại (nếu có thay đổi)
        if (!request.getSoGiayPhep().equals(bacSi.getSoGiayPhep()) &&
                bacSiRepository.existsBySoGiayPhep(request.getSoGiayPhep())) {
            throw new RuntimeException("Số giấy phép đã tồn tại");
        }

        // Kiểm tra chuyên khoa và khoa tồn tại
        ChuyenKhoaEntity chuyenKhoa = chuyenKhoaRepository.findById(request.getIdChuyenKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyên khoa"));
        
        KhoaEntity khoa = khoaRepository.findById(request.getIdKhoa())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa"));

        // Cập nhật thông tin người dùng
        nguoiDung.setHoTen(request.getHoTen());
        nguoiDung.setSoDienThoai(request.getSoDienThoai());
        nguoiDung.setDiaChi(request.getDiaChi());
        nguoiDung.setNgaySinh(request.getNgaySinh() != null ? LocalDate.parse(request.getNgaySinh()) : null);
        nguoiDung.setGioiTinh(request.getGioiTinh());
        nguoiDung.setCccd(request.getCccd());

        nguoiDungRepository.save(nguoiDung);

        // Cập nhật thông tin bác sĩ
        bacSi.setChuyenKhoa(chuyenKhoa);
        bacSi.setKhoa(khoa);
        bacSi.setChuyenMon(request.getChuyenMon());
        bacSi.setSoGiayPhep(request.getSoGiayPhep());
        bacSi.setSoNamKinhNghiem(request.getSoNamKinhNghiem());
        bacSi.setDangLamViec(request.getDangLamViec() != null ? request.getDangLamViec() : bacSi.getDangLamViec());
        bacSi.setAnhDaiDien(request.getAnhDaiDien());

        return convertToResponse(bacSiRepository.save(bacSi));
    }

    @Transactional
    public void deleteBacSi(Integer id) {
        BacSiEntity bacSi = bacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bác sĩ"));

        // Xóa bác sĩ sẽ tự động xóa người dùng (do có CASCADE)
        bacSiRepository.delete(bacSi);
    }

    private BacSiResponse convertToResponse(BacSiEntity bacSi) {
        NguoiDungEntity nguoiDung = bacSi.getNguoiDung();
        return BacSiResponse.builder()
                .idBacSi(bacSi.getId())
                .idNguoiDung(nguoiDung.getIdNguoiDung())
                .email(nguoiDung.getEmail())
                .hoTen(nguoiDung.getHoTen())
                .soDienThoai(nguoiDung.getSoDienThoai())
                .diaChi(nguoiDung.getDiaChi())
                .ngaySinh(nguoiDung.getNgaySinh() != null ? nguoiDung.getNgaySinh().toString() : null)
                .gioiTinh(nguoiDung.getGioiTinh())
                .cccd(nguoiDung.getCccd())
                .idChuyenKhoa(bacSi.getChuyenKhoa().getIdChuyenKhoa())
                .tenChuyenKhoa(bacSi.getChuyenKhoa().getTenChuyenKhoa())
                .idKhoa(bacSi.getKhoa().getIdKhoa())
                .tenKhoa(bacSi.getKhoa().getTenKhoa())
                .chuyenMon(bacSi.getChuyenMon())
                .soGiayPhep(bacSi.getSoGiayPhep())
                .soNamKinhNghiem(bacSi.getSoNamKinhNghiem())
                .dangLamViec(bacSi.getDangLamViec())
                .daKichHoat(nguoiDung.getDaKichHoat())
                .anhDaiDien(bacSi.getAnhDaiDien())
                .build();
    }
} 