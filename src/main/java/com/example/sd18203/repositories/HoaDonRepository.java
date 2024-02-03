package com.example.sd18203.repositories;

import com.example.sd18203.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    public static final int CHO_THANH_TOAN = 0;
    public static final int DA_THANH_TOAN = 1;

    public List<HoaDon> findByTrangThai(int trangThai);

    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
}
