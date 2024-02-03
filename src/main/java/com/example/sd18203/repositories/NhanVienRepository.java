package com.example.sd18203.repositories;

import com.example.sd18203.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    public static final int DANG_LAM_VIEC = 1;
    public static final int DA_NGHI_VIEC = 0;

    public List<NhanVien> findByTrangThai(int trangThai);

    public NhanVien findByTenDangNhapAndMatKhauAndTrangThai(String tenDangNhap, String matKhau, int trangThai);
}
