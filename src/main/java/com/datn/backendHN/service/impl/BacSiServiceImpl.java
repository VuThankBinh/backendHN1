package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.BacSiDTO;
import com.datn.backendHN.dto.ThanhVienDTO;
import com.datn.backendHN.entity.BacSi;
import com.datn.backendHN.entity.ChucVu;
import com.datn.backendHN.entity.ThanhVien;
import com.datn.backendHN.repository.BacSiRepository;
import com.datn.backendHN.repository.ChucVuRepository;
import com.datn.backendHN.repository.ChuyenKhoaRepository;
import com.datn.backendHN.repository.PhongKhamRepository;
import com.datn.backendHN.repository.ThanhVienRepository;
import com.datn.backendHN.service.BacSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BacSiServiceImpl implements BacSiService {

    @Autowired
    private BacSiRepository bacSiRepository;

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Autowired
    private ChuyenKhoaRepository chuyenKhoaRepository;

    @Autowired
    private PhongKhamRepository phongKhamRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public BacSi createBacSi(BacSiDTO bacSiDTO) {
        // Validate input
        if (bacSiDTO == null || bacSiDTO.getThanhVien() == null) {
            throw new RuntimeException("Dữ liệu không hợp lệ");
        }

        ThanhVienDTO thanhVienDTO = bacSiDTO.getThanhVien();
        System.out.println("Debug - Raw DTO data:");
        System.out.println("bacSiDTO: " + bacSiDTO);
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
            byte[] bytes = hoTen.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
            hoTen = new String(bytes, java.nio.charset.StandardCharsets.UTF_8).trim();
        } catch (Exception e) {
            System.out.println("Debug - Error encoding hoTen: " + e.getMessage());
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
        thanhVien.setVaiTro("BAC_SI");
        
        System.out.println("Debug - thanhVien before save: " + thanhVien);
        try {
            thanhVien = thanhVienRepository.save(thanhVien);
            System.out.println("Debug - thanhVien after save: " + thanhVien);
            
            if (thanhVien.getId() == null) {
                throw new RuntimeException("Không thể lưu thành viên");
            }
        } catch (Exception e) {
            System.out.println("Debug - Error saving thanhVien: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu thành viên: " + e.getMessage());
        }

        // Create BacSi
        try {
            BacSi bacSi = new BacSi();
            bacSi.setTaiKhoanId(thanhVien.getId());
            
        //     // Lấy thông tin chức vụ
        //     ChucVu chucVu = chucVuRepository.findById(bacSiDTO.getChucVuId())
        //             .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
        //     bacSi.setChucVuId(chucVu.getId());
            bacSi.setTenChucVu(bacSiDTO.getTenChucVu());
            
            bacSi.setChuyenKhoaId(bacSiDTO.getChuyenKhoaId());
            bacSi.setPhongKhamId(bacSiDTO.getPhongKhamId());
            bacSi.setBangCap(bacSiDTO.getBangCap());
            bacSi.setKinhNghiem(bacSiDTO.getKinhNghiem());

            return bacSiRepository.save(bacSi);
        } catch (Exception e) {
            // Nếu lưu BacSi thất bại, xóa ThanhVien đã lưu
            thanhVienRepository.delete(thanhVien);
            throw new RuntimeException("Lỗi khi lưu bác sĩ: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public BacSi updateBacSi(Integer id, BacSiDTO bacSiDTO) {
        BacSi bacSi = bacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bác sĩ không tồn tại"));

        ThanhVien thanhVien = thanhVienRepository.findById(bacSi.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin tài khoản"));
        
        ThanhVienDTO thanhVienDTO = bacSiDTO.getThanhVien();
        
        // Xử lý encoding cho họ tên
        String hoTen = thanhVienDTO.getHoTen();
        try {
            byte[] bytes = hoTen.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
            hoTen = new String(bytes, java.nio.charset.StandardCharsets.UTF_8).trim();
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
        
        thanhVienRepository.save(thanhVien);

        // Cập nhật thông tin chức vụ
        bacSi.setTenChucVu(bacSiDTO.getTenChucVu());

        bacSi.setChuyenKhoaId(bacSiDTO.getChuyenKhoaId());
        bacSi.setPhongKhamId(bacSiDTO.getPhongKhamId());
        bacSi.setBangCap(bacSiDTO.getBangCap());
        bacSi.setKinhNghiem(bacSiDTO.getKinhNghiem());

        return bacSiRepository.save(bacSi);
    }

    @Override
    @Transactional
    public void deleteBacSi(Integer id) {
        BacSi bacSi = bacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bác sĩ không tồn tại"));
        ThanhVien thanhVien = thanhVienRepository.findById(bacSi.getTaiKhoanId())
                .orElse(null);
        bacSiRepository.delete(bacSi);
        if (thanhVien != null) {
            thanhVienRepository.delete(thanhVien);
        }
    }

    @Override
    public BacSi getBacSiById(Integer id) {
        return bacSiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bác sĩ không tồn tại"));
    }

    @Override
    public List<BacSi> getAllBacSi() {
        return bacSiRepository.findAll();
    }

    @Override
    public List<BacSi> getBacSiByChuyenKhoa(Integer chuyenKhoaId) {
        return bacSiRepository.findByChuyenKhoaId(chuyenKhoaId);
    }

    @Override
    public List<BacSi> getBacSiByPhongKham(Integer phongKhamId) {
        return bacSiRepository.findByPhongKhamId(phongKhamId);
    }
} 