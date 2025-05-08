package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.LeTanDTO;
import com.datn.backendHN.dto.ThanhVienDTO;
import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.repository.ThanhVienRepository;
import com.datn.backendHN.service.LeTanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class LeTanServiceImpl implements LeTanService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ThanhVien createLeTan(LeTanDTO leTanDTO) {
        // Validate input
        if (leTanDTO == null || leTanDTO.getThanhVien() == null) {
            throw new RuntimeException("Dữ liệu không hợp lệ");
        }

        ThanhVienDTO thanhVienDTO = leTanDTO.getThanhVien();
        System.out.println("Debug - Raw DTO data:");
        System.out.println("leTanDTO: " + leTanDTO);
        System.out.println("thanhVienDTO: " + thanhVienDTO);
        System.out.println("hoTen: [" + thanhVienDTO.getHoTen() + "]");
        System.out.println("soDienThoai: [" + thanhVienDTO.getSoDienThoai() + "]");
        System.out.println("tenDangNhap: [" + thanhVienDTO.getTenDangNhap() + "]");
        System.out.println("matKhau: [" + thanhVienDTO.getMatKhau() + "]");

        // Validate và xử lý encoding
        String hoTen = thanhVienDTO.getHoTen();
        if (hoTen == null || hoTen.trim().isEmpty()) {
            throw new RuntimeException("Họ tên không được để trống");
        }
        
        // Xử lý encoding cho họ tên
        try {
            byte[] bytes = hoTen.getBytes(StandardCharsets.ISO_8859_1);
            hoTen = new String(bytes, StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            System.out.println("Debug - Error encoding hoTen: " + e.getMessage());
            // Nếu có lỗi encoding, giữ nguyên giá trị gốc
            hoTen = hoTen.trim();
        }
        System.out.println("Debug - hoTen after encoding: [" + hoTen + "]");

        if (thanhVienDTO.getSoDienThoai() == null || thanhVienDTO.getSoDienThoai().trim().isEmpty()) {
            throw new RuntimeException("Số điện thoại không được để trống");
        }
        if (thanhVienDTO.getTenDangNhap() == null || thanhVienDTO.getTenDangNhap().trim().isEmpty()) {
            throw new RuntimeException("Tên đăng nhập không được để trống");
        }
        if (thanhVienDTO.getMatKhau() == null || thanhVienDTO.getMatKhau().trim().isEmpty()) {
            throw new RuntimeException("Mật khẩu không được để trống");
        }

        // Create ThanhVien
        ThanhVien thanhVien = new ThanhVien();
        thanhVien.setHoTen(hoTen);
        thanhVien.setSoDienThoai(thanhVienDTO.getSoDienThoai().trim());
        thanhVien.setEmail(thanhVienDTO.getEmail() != null ? thanhVienDTO.getEmail().trim() : null);
        thanhVien.setDiaChi(thanhVienDTO.getDiaChi() != null ? thanhVienDTO.getDiaChi().trim() : null);
        thanhVien.setTenDangNhap(thanhVienDTO.getTenDangNhap().trim());
        thanhVien.setMatKhau(passwordEncoder.encode(thanhVienDTO.getMatKhau()));
        thanhVien.setVaiTro("LE_TAN");
        
        System.out.println("Debug - thanhVien before save: " + thanhVien);
        try {
            thanhVien = thanhVienRepository.save(thanhVien);
            System.out.println("Debug - thanhVien after save: " + thanhVien);
            
            if (thanhVien.getId() == null) {
                throw new RuntimeException("Không thể lưu thành viên");
            }
            return thanhVien;
        } catch (Exception e) {
            System.out.println("Debug - Error saving thanhVien: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu thành viên: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ThanhVien updateLeTan(Integer id, LeTanDTO leTanDTO) {
        ThanhVien thanhVien = thanhVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lễ tân không tồn tại"));
        
        ThanhVienDTO thanhVienDTO = leTanDTO.getThanhVien();
        
        // Xử lý encoding cho họ tên
        String hoTen = thanhVienDTO.getHoTen();
        try {
            byte[] bytes = hoTen.getBytes(StandardCharsets.ISO_8859_1);
            hoTen = new String(bytes, StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            System.out.println("Debug - Error encoding hoTen: " + e.getMessage());
            hoTen = hoTen.trim();
        }
        
        thanhVien.setHoTen(hoTen);
        thanhVien.setSoDienThoai(thanhVienDTO.getSoDienThoai());
        thanhVien.setEmail(thanhVienDTO.getEmail());
        thanhVien.setDiaChi(thanhVienDTO.getDiaChi());
        
        if (thanhVienDTO.getMatKhau() != null && !thanhVienDTO.getMatKhau().isEmpty()) {
            thanhVien.setMatKhau(passwordEncoder.encode(thanhVienDTO.getMatKhau()));
        }
        
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    @Transactional
    public void deleteLeTan(Integer id) {
        thanhVienRepository.deleteById(id);
    }

    @Override
    public ThanhVien getLeTanById(Integer id) {
        return thanhVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lễ tân không tồn tại"));
    }

    @Override
    public List<ThanhVien> getAllLeTan() {
        return thanhVienRepository.findByVaiTro("LE_TAN");
    }
} 