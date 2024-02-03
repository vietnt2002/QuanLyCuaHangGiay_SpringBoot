package com.example.sd18203.repositories;

import com.example.sd18203.entities.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {
    public static final int CON_HANG = 1;
    public static final int HET_HANG = 0;

    public Page<ChiTietSanPham> findByTrangThai(int trangThai, Pageable pageable);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.idSanPham.id = :idSP")
    public List<ChiTietSanPham> findByIdSanPham(@Param("idSP") int idSP);
}
