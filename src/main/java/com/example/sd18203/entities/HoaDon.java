package com.example.sd18203.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "HoaDon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDon {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "IdNV")
    @ManyToOne
    private NhanVien idNV;

    @JoinColumn(name = "IdKH")
    @ManyToOne
    private KhachHang idKH;

    @Temporal(TemporalType.DATE)
    @Column(name = "NgayMuaHang")
    private Date ngayMuaHang;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
