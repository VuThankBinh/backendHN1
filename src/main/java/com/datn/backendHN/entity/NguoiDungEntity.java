package com.datn.backendHN.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import com.datn.backendHN.enums.VaiTro;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "nguoi_dung")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDungEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nguoi_dung")
    private Integer idNguoiDung;
    
    @Column(name = "email", unique = true)
    private String email;
    
    @Column(name = "mat_khau")
    private String matKhau;
    
    @Column(name = "ho_ten")
    private String hoTen;
    
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    
    @Column(name = "dia_chi")
    private String diaChi;
    
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;
    
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    
    @Column(name = "cccd", unique = true)
    private String cccd;
    
    @Column(name = "vai_tro")
    private String vaiTro;
    
    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;
    
    @Column(name = "ngay_tao")
    private LocalDate ngayTao;
    
    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + vaiTro));
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return email;
    }
    
    
   
}
