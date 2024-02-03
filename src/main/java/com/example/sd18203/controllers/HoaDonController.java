package com.example.sd18203.controllers;

import com.example.sd18203.dto.HoaDonRequest;
import com.example.sd18203.entities.HoaDon;
import com.example.sd18203.entities.KhachHang;
import com.example.sd18203.entities.NhanVien;
import com.example.sd18203.repositories.HoaDonRepository;
import com.example.sd18203.repositories.KhachHangRepository;
import com.example.sd18203.repositories.NhanVienRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Autowired
    private NhanVienRepository nhanVienRepo;
    @Autowired
    private KhachHangRepository khachHangRepo;


    @GetMapping("show")
    public String getIndex(Model model) {
        model.addAttribute("listHoaDon", hoaDonRepo.findAll());
        return "admin/hoa_don/index";
    }

    @GetMapping("create")
    public String create(
            Model model
    ) {
        HoaDonRequest hoaDonRequest = new HoaDonRequest();
        model.addAttribute("hoaDon", hoaDonRequest);
        model.addAttribute("listNhanVien", nhanVienRepo.findAll());
        model.addAttribute("listKhachHang", khachHangRepo.findAll());
        return "admin/hoa_don/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute HoaDonRequest hdReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/hoa_don/create";
        }
        NhanVien nhanVien = nhanVienRepo.findById(hdReq.getIdNV()).get();
        KhachHang khachHang = khachHangRepo.findById(hdReq.getIdKH()).get();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setIdNV(nhanVien);
            hoaDon.setIdKH(khachHang);
            hoaDon.setNgayMuaHang(hdReq.getNgayMuaHang());
            hoaDon.setTrangThai(hdReq.getTrangThai());
            hoaDonRepo.save(hoaDon);
        }catch (Exception e){
            e.printStackTrace();
        }


        return "redirect:/hoa-don/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ) {
        hoaDonRepo.deleteById(id);
        return "redirect:/hoa-don/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") HoaDon hoaDon,
            Model model
    ) {
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listNhanVien", nhanVienRepo.findAll());
        model.addAttribute("listKhachHang", khachHangRepo.findAll());
        return "admin/hoa_don/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") HoaDon hoaDon,
            @Valid @ModelAttribute HoaDonRequest hdReq,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/hoa_don/edit";
        }

        NhanVien nhanVien = nhanVienRepo.findById(hdReq.getIdNV()).get();
        KhachHang khachHang = khachHangRepo.findById(hdReq.getIdKH()).get();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            hoaDon.setIdNV(nhanVien);
            hoaDon.setIdKH(khachHang);
            hoaDon.setNgayMuaHang(hdReq.getNgayMuaHang());
            hoaDon.setTrangThai(hdReq.getTrangThai());
            hoaDonRepo.save(hoaDon);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/hoa-don/show";
    }
}



