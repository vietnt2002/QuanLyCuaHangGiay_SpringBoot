package com.example.sd18203.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDonRequest {
    private Integer id;

    private Integer idNV;

    private Integer idKH;

    @NotNull(message = "Không được để trống!")
    private Date ngayMuaHang;

    @NotNull(message = "Không được để trống!")
    private Integer trangThai;
}
