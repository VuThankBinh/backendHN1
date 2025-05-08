package com.datn.backendHN.service.impl;

import com.datn.backendHN.dto.ChucVuDTO;
import com.datn.backendHN.entity.ChucVu;
import com.datn.backendHN.repository.ChucVuRepository;
import com.datn.backendHN.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChucVuServiceImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    @Transactional
    public ChucVu createChucVu(ChucVuDTO chucVuDTO) {
        ChucVu chucVu = new ChucVu();
        chucVu.setTenChucVu(chucVuDTO.getTenChucVu());
        return chucVuRepository.save(chucVu);
    }

    @Override
    @Transactional
    public ChucVu updateChucVu(Integer id, ChucVuDTO chucVuDTO) {
        ChucVu chucVu = chucVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
        
        chucVu.setTenChucVu(chucVuDTO.getTenChucVu());
        
        return chucVuRepository.save(chucVu);
    }

    @Override
    @Transactional
    public void deleteChucVu(Integer id) {
        ChucVu chucVu = chucVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
        chucVuRepository.delete(chucVu);
    }

    @Override
    public ChucVu getChucVuById(Integer id) {
        return chucVuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại"));
    }

    @Override
    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.findAll();
    }
} 