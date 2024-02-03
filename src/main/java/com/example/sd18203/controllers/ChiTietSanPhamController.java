package com.example.sd18203.controllers;

import com.example.sd18203.dto.ChiTietSanPhamRequest;
import com.example.sd18203.entities.ChiTietSanPham;
import com.example.sd18203.entities.KichThuoc;
import com.example.sd18203.entities.MauSac;
import com.example.sd18203.entities.SanPham;
import com.example.sd18203.repositories.ChiTietSanPhamRepository;
import com.example.sd18203.repositories.KichThuocRepository;
import com.example.sd18203.repositories.MauSacRepository;
import com.example.sd18203.repositories.SanPhamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chi-tiet-san-pham")
public class ChiTietSanPhamController {
    @Autowired
    ChiTietSanPhamRepository chiTietSanPhamRepo;
    @Autowired
    KichThuocRepository kichThuocRepo;
    @Autowired
    MauSacRepository mauSacRepo;
    @Autowired
    SanPhamRepository sanPhamRepo;

    @GetMapping("show")
    public String getIndex(Model model) {
        model.addAttribute("listChiTietSanPham", chiTietSanPhamRepo.findAll());
        return "admin/chi_tiet_san_pham/index";
    }

    @GetMapping("create")
    public String create(Model model) {
        ChiTietSanPhamRequest ctsp = new ChiTietSanPhamRequest();
        model.addAttribute("chiTietSanPham", ctsp);
        model.addAttribute("listSanPham", sanPhamRepo.findAll());
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        model.addAttribute("listMauSac", mauSacRepo.findAll());
        return "admin/chi_tiet_san_pham/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("chiTietSanPham") ChiTietSanPhamRequest ctspReq,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("listSanPham", sanPhamRepo.findAll());
            model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
            model.addAttribute("listMauSac", mauSacRepo.findAll());
            return "admin/chi_tiet_san_pham/create";
        }
        KichThuoc kichThuoc = kichThuocRepo.findById(ctspReq.getIdKichThuoc()).get();
        MauSac mauSac = mauSacRepo.findById(ctspReq.getIdMauSac()).get();
        SanPham sanPham = sanPhamRepo.findById(ctspReq.getIdSanPham()).get();

        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setMa(ctspReq.getMa());
        chiTietSanPham.setIdKichThuoc(kichThuoc);
        chiTietSanPham.setIdSanPham(sanPham);
        chiTietSanPham.setIdMauSac(mauSac);
        chiTietSanPham.setSoLuong(ctspReq.getSoLuong());
        chiTietSanPham.setDonGia(ctspReq.getDonGia());
        chiTietSanPham.setTrangThai(ctspReq.getTrangThai());
        chiTietSanPhamRepo.save(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ) {
        chiTietSanPhamRepo.deleteById(id);
        return "redirect:/chi-tiet-san-pham/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") ChiTietSanPham chiTietSanPham,
            Model model
    ) {

        model.addAttribute("chiTietSanPham", chiTietSanPham);
        model.addAttribute("listSanPham", sanPhamRepo.findAll());
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        model.addAttribute("listMauSac", mauSacRepo.findAll());
        return "admin/chi_tiet_san_pham/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") ChiTietSanPham chiTietSanPham,
            @Valid @ModelAttribute("chiTietSanPham") ChiTietSanPhamRequest ctspReq,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("listSanPham", sanPhamRepo.findAll());
            model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
            model.addAttribute("listMauSac", mauSacRepo.findAll());
            return "admin/chi_tiet_san_pham/edit";
        }
        KichThuoc kichThuoc = kichThuocRepo.findById(ctspReq.getIdKichThuoc()).get();
        MauSac mauSac = mauSacRepo.findById(ctspReq.getIdMauSac()).get();
        SanPham sanPham = sanPhamRepo.findById(ctspReq.getIdSanPham()).get();

        chiTietSanPham.setMa(ctspReq.getMa());
        chiTietSanPham.setIdKichThuoc(kichThuoc);
        chiTietSanPham.setIdMauSac(mauSac);
        chiTietSanPham.setIdSanPham(sanPham);
        chiTietSanPham.setSoLuong(ctspReq.getSoLuong());
        chiTietSanPham.setDonGia(ctspReq.getDonGia());
        chiTietSanPham.setTrangThai(ctspReq.getTrangThai());
        chiTietSanPhamRepo.save(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/show";
    }
}

