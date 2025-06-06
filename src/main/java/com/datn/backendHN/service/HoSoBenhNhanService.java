package com.datn.backendHN.service;

import java.util.List;
import java.util.Optional;

import com.datn.backendHN.entity.HoSoBenhNhan;

public interface HoSoBenhNhanService {
    List<HoSoBenhNhan> getAllHoSoBenhNhan();
    Optional<HoSoBenhNhan> getHoSoBenhNhanById(Integer id);
    List<HoSoBenhNhan> getHoSoBenhNhanByNguoiDungId(Integer nguoiDungId);
    HoSoBenhNhan createHoSoBenhNhan(HoSoBenhNhan hoSoBenhNhan);
    HoSoBenhNhan updateHoSoBenhNhan(Integer id, HoSoBenhNhan hoSoBenhNhan);
    void deleteHoSoBenhNhan(Integer id);
} 