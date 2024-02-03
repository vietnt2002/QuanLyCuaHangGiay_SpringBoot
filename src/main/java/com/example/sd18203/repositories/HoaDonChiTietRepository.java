package com.example.sd18203.repositories;

import com.example.sd18203.entities.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    public static final int CHO_THANH_TOAN = 0;
    public static final int DA_THANH_TOAN = 1;

    @Query("select hdct from HoaDonChiTiet hdct where hdct.idHD.id = :idHD")
    public List<HoaDonChiTiet> findByIdHD(@Param("idHD") int idHD);

    @Query("select hdct from HoaDonChiTiet hdct where hdct.idHD.id = :idHD and hdct.idCTSP.id = :idCTSP")
    public HoaDonChiTiet findByIdHDAndIdCTSP(@Param("idHD") int idHD, @Param("idCTSP") int idCTSP);
}
