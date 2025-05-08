package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.ThuocDTO;
import com.datn.backendHN.entity.Thuoc;
import com.datn.backendHN.repository.ThuocRepository;
import com.datn.backendHN.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThuocServiceImpl implements ThuocService {

    @Autowired
    private ThuocRepository thuocRepository;

    @Override
    @Transactional
    public Thuoc createThuoc(ThuocDTO thuocDTO) {
        Thuoc thuoc = new Thuoc();
        thuoc.setTenThuoc(thuocDTO.getTenThuoc());
        thuoc.setDonVi(thuocDTO.getDonVi());
        thuoc.setLieuLuong(thuocDTO.getLieuLuong());
        thuoc.setTacDung(thuocDTO.getTacDung());
        return thuocRepository.save(thuoc);
    }

    @Override
    @Transactional
    public Thuoc updateThuoc(Integer id, ThuocDTO thuocDTO) {
        Thuoc thuoc = thuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thuốc không tồn tại"));

        thuoc.setTenThuoc(thuocDTO.getTenThuoc());
        thuoc.setDonVi(thuocDTO.getDonVi());
        thuoc.setLieuLuong(thuocDTO.getLieuLuong());
        thuoc.setTacDung(thuocDTO.getTacDung());

        return thuocRepository.save(thuoc);
    }

    @Override
    @Transactional
    public void deleteThuoc(Integer id) {
        Thuoc thuoc = thuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thuốc không tồn tại"));
        thuocRepository.delete(thuoc);
    }

    @Override
    public Thuoc getThuocById(Integer id) {
        return thuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thuốc không tồn tại"));
    }

    @Override
    public List<Thuoc> getAllThuoc() {
        return thuocRepository.findAll();
    }

    @Override
    public List<Thuoc> getThuocByTrangThai(String trangThai) {
        throw new UnsupportedOperationException("Chức năng này không được hỗ trợ");
    }

    @Override
    public List<Thuoc> searchThuoc(String keyword) {
        return thuocRepository.findByTenThuocContaining(keyword);
    }

    @Override
    @Transactional
    public void updateSoLuong(Integer id, Integer soLuong) {
        throw new UnsupportedOperationException("Chức năng này không được hỗ trợ");
    }
} 