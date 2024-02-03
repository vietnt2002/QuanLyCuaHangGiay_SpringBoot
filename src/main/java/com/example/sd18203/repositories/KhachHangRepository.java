package com.example.sd18203.repositories;

import com.example.sd18203.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

    public KhachHang findBySdt(String sdt);
}
