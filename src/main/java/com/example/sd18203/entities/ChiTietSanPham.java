package com.example.sd18203.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SanPhamChiTiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietSanPham{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MaSPCT")
    private String ma;

    @JoinColumn(name = "IdKichThuoc")
    @ManyToOne
    private KichThuoc idKichThuoc;

    @JoinColumn(name = "IdMauSac")
    @ManyToOne
    private MauSac idMauSac;

    @JoinColumn(name = "IdSanPham")
    @ManyToOne
    private SanPham idSanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private BigDecimal donGia;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
