package com.example.sd18203.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietSanPhamRequest {
    private Integer id;

    @NotBlank(message = "Không được để trống")
    private String ma;

    private Integer idKichThuoc;

    private Integer idMauSac;

    private Integer idSanPham;

    @NotNull(message = "Không được để trống")
    private Integer soLuong;

    @NotNull(message = "Không được để trống")
    private BigDecimal donGia;

    @NotNull(message = "Không được để trống")
    private Integer trangThai;
}
