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
    
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    
    @Column(name = "mat_khau", length = 255, nullable = false)
    private String matKhau;
    
    @Column(name = "ho_ten", length = 100, nullable = false)
    private String hoTen;
    
    @Column(name = "so_dien_thoai", length = 15, nullable = false)
    private String soDienThoai;
    
    @Column(name = "dia_chi", length = 255)
    private String diaChi;
    
    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;
    
    @Column(name = "gioi_tinh", length = 10)
    private String gioiTinh;
    
    @Column(name = "cccd", length = 20)
    private String cccd;
    
    @Column(name = "vai_tro", length = 20, nullable = false)
    private String vaiTro;
    
    @Column(name = "da_kich_hoat")
    private Boolean daKichHoat;
    
    @Column(name = "ngay_tao")
    private LocalDate ngayTao;
    
    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @Column(name = "avatar", columnDefinition = "nvarchar(MAX)")
    private String avatar;

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
        return true;
    }
}
