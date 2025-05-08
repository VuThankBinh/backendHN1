package com.datn.backendHN.service;

import com.datn.backendHN.dto.ThuocDTO;
import com.datn.backendHN.entity.Thuoc;
import java.util.List;

public interface ThuocService {
    Thuoc createThuoc(ThuocDTO thuocDTO);
    Thuoc updateThuoc(Integer id, ThuocDTO thuocDTO);
    void deleteThuoc(Integer id);
    Thuoc getThuocById(Integer id);
    List<Thuoc> getAllThuoc();
    List<Thuoc> getThuocByTrangThai(String trangThai);
    List<Thuoc> searchThuoc(String keyword);
    void updateSoLuong(Integer id, Integer soLuong);
} 