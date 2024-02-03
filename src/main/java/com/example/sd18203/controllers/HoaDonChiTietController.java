package com.example.sd18203.controllers;

import com.example.sd18203.dto.HoaDonChiTietRequest;
import com.example.sd18203.entities.*;
import com.example.sd18203.repositories.ChiTietSanPhamRepository;
import com.example.sd18203.repositories.HoaDonChiTietRepository;
import com.example.sd18203.repositories.HoaDonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepo;
    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepo;

    @GetMapping("show")
    public String getIndex(Model model) {
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietRepo.findAll());
        return "admin/hoa_don_chi_tiet/index";
    }

    @GetMapping("create")
    public String create(Model model) {
        HoaDonChiTietRequest hoaDonChiTietReq = new HoaDonChiTietRequest();
        model.addAttribute("hoaDonChiTiet", hoaDonChiTietReq);
        model.addAttribute("listHoaDon", hoaDonRepo.findAll());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
        return "admin/hoa_don_chi_tiet/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("hoaDonChiTiet") HoaDonChiTietRequest hdctReq,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("listHoaDon", hoaDonRepo.findAll());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
            return "admin/hoa_don_chi_tiet/create";
        }
        HoaDon hoaDon = hoaDonRepo.findById(hdctReq.getIdHD()).get();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(hdctReq.getIdCTSP()).get();

        try {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setIdHD(hoaDon);
            hoaDonChiTiet.setIdCTSP(chiTietSanPham);
            hoaDonChiTiet.setSoLuong(hdctReq.getSoLuong());
            hoaDonChiTiet.setDonGia(hdctReq.getDonGia());
            hoaDonChiTiet.setThoiGian(hdctReq.getThoiGian());
            hoaDonChiTiet.setTrangThai(hdctReq.getTrangThai());
            hoaDonChiTietRepo.save(hoaDonChiTiet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/hoa-don-chi-tiet/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ) {
        hoaDonChiTietRepo.deleteById(id);
        return "redirect:/hoa-don-chi-tiet/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") HoaDonChiTiet hoaDonChiTiet,
            Model model
    ) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);
        model.addAttribute("listHoaDon", hoaDonRepo.findAll());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
        return "admin/hoa_don_chi_tiet/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") HoaDonChiTiet hoaDonChiTiet,
            @Valid @ModelAttribute("hoaDonChiTiet") HoaDonChiTietRequest hdctReq,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("listHoaDon", hoaDonRepo.findAll());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
            return "admin/hoa_don_chi_tiet/edit";
        }

        HoaDon hoaDon = hoaDonRepo.findById(hdctReq.getIdHD()).get();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepo.findById(hdctReq.getIdCTSP()).get();

        hoaDonChiTiet.setIdHD(hoaDon);
        hoaDonChiTiet.setIdCTSP(chiTietSanPham);
        hoaDonChiTiet.setSoLuong(hdctReq.getSoLuong());
        hoaDonChiTiet.setDonGia(hdctReq.getDonGia());
        hoaDonChiTiet.setThoiGian(hdctReq.getThoiGian());
        hoaDonChiTiet.setTrangThai(hdctReq.getTrangThai());

        hoaDonChiTietRepo.save(hoaDonChiTiet);
        return "redirect:/hoa-don-chi-tiet/show";
    }
}
