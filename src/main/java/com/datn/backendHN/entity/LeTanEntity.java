package com.datn.backendHN.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "le_tan")
public class LeTanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_le_tan")
    private Integer idLeTan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_nguoi_dung", referencedColumnName = "id_nguoi_dung")
    private NguoiDungEntity nguoiDung;

    @Column(name = "ca_lam_viec")
    @Enumerated(EnumType.STRING)
    private CaLamViec caLamViec;

    public enum CaLamViec {
        SANG,
        CHIEU,
        TOI
    }
} 