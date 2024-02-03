package com.example.sd18203.repositories;

import com.example.sd18203.entities.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac, Integer> {
    public static final int HOAT_DONG = 0;
    public static final int KHONG_HOAT_DONG = 1;

//    @Query("select ms from MauSac ms where ms.trangThai = :trangThai")
//    public List<MauSac> findByTrangThai(@Param("trangThai") int trangThai);


    //DSL
    public Page<MauSac> findByTrangThai(int trangThai, Pageable pageable);
}
