package com.datn.backendHN.service.impl;

import com.datn.backendHN.entity.HoSoBenhNhan;
import com.datn.backendHN.repository.HoSoBenhNhanRepository;
import com.datn.backendHN.service.HoSoBenhNhanService;
import com.datn.backendHN.exception.DuplicateResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoSoBenhNhanServiceImpl implements HoSoBenhNhanService {

    @Autowired
    private HoSoBenhNhanRepository hoSoBenhNhanRepository;

    @Override
    public List<HoSoBenhNhan> getAllHoSoBenhNhan() {
        return hoSoBenhNhanRepository.findAll();
    }

    @Override
    public Optional<HoSoBenhNhan> getHoSoBenhNhanById(Integer id) {
        return hoSoBenhNhanRepository.findById(id);
    }

        @Override
    public List<HoSoBenhNhan> getHoSoBenhNhanByNguoiDungId(Integer nguoiDungId) {
        return hoSoBenhNhanRepository.findByNguoiDungId(nguoiDungId);
    }

    @Override
    public HoSoBenhNhan createHoSoBenhNhan(HoSoBenhNhan hoSoBenhNhan) {
        // Kiểm tra trùng lặp mã định danh (CCCD)
        if (hoSoBenhNhan.getMaDinhDanh() != null && hoSoBenhNhanRepository.existsByMaDinhDanh(hoSoBenhNhan.getMaDinhDanh())) {
            throw new DuplicateResourceException("Mã định danh (CCCD) đã tồn tại trong hệ thống");
        }

        return hoSoBenhNhanRepository.save(hoSoBenhNhan);
    }

    @Override
    public HoSoBenhNhan updateHoSoBenhNhan(Integer id, HoSoBenhNhan hoSoBenhNhan) {
        if (hoSoBenhNhanRepository.existsById(id)) {
            // Kiểm tra trùng lặp mã định danh (trừ trường hợp của chính hồ sơ này)
            if (hoSoBenhNhan.getMaDinhDanh() != null) {
                Optional<HoSoBenhNhan> existingByCCCD = hoSoBenhNhanRepository.findAll().stream()
                    .filter(hs -> hs.getMaDinhDanh().equals(hoSoBenhNhan.getMaDinhDanh()) && !hs.getMaHoSo().equals(id))
                    .findFirst();
                if (existingByCCCD.isPresent()) {
                    throw new DuplicateResourceException("Mã định danh (CCCD) đã tồn tại trong hệ thống");
                }
            }

            hoSoBenhNhan.setMaHoSo(id);
            return hoSoBenhNhanRepository.save(hoSoBenhNhan);
        }
        return null;
    }

    @Override
    public void deleteHoSoBenhNhan(Integer id) {
        hoSoBenhNhanRepository.deleteById(id);
    }
} 