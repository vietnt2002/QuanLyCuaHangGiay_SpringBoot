package com.example.sd18203.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HoaDonChiTietRequest {
    private Integer id;

    private Integer idHD;

    private Integer idCTSP;

    @NotNull(message = "Không được để trống!")
    private Integer soLuong;

    @NotNull(message = "Không được để trống!")
    private BigDecimal donGia;

    @NotNull(message = "Không được để trống!")
    private Date thoiGian;

    @NotNull(message = "Không được để trống!")
    private Integer trangThai;
}
