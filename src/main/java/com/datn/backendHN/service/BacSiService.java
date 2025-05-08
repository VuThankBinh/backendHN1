package com.datn.backendHN.service;

import com.datn.backendHN.dto.BacSiDTO;
import com.datn.backendHN.entity.BacSi;

import java.util.List;

public interface BacSiService {
    BacSi createBacSi(BacSiDTO bacSiDTO);
    BacSi updateBacSi(Integer id, BacSiDTO bacSiDTO);
    void deleteBacSi(Integer id);
    BacSi getBacSiById(Integer id);
    List<BacSi> getAllBacSi();
    List<BacSi> getBacSiByChuyenKhoa(Integer chuyenKhoaId);
    List<BacSi> getBacSiByPhongKham(Integer phongKhamId);
} 