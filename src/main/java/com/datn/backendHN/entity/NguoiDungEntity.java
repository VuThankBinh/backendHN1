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

@Entity
@Table(name = "NGUOI_DUNG")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDungEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_nguoi_dung;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "mat_khau", nullable = false)
    private String matKhau;
    
    @Column(name = "ho_ten", nullable = false)
    private String hoTen;
    
    @Column(name = "so_dien_thoai", nullable = false)
    private String soDienThoai;
    
    @Column(name = "dia_chi")
    private String diaChi;
    
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;
    
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    
    @Column(name = "cccd", nullable = false, unique = true)
    private String cccd;
    
    @Column(name = "vai_tro", nullable = false)
    private String vaiTro;
    
    @Column(name = "da_kich_hoat", nullable = false)
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return daKichHoat;
    }
}
