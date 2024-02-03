package com.example.sd18203.controllers;

import com.example.sd18203.dto.KhachHangRequest;
import com.example.sd18203.entities.KhachHang;
import com.example.sd18203.repositories.KhachHangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangRepository khachHangRepo;

    @GetMapping("show")
    public String getIndex(Model model){
        model.addAttribute("listKhachHang", khachHangRepo.findAll());
        return "admin/khach_hang/index";
    }

    @GetMapping("create")
    public String create(Model model){
        KhachHangRequest khachHangRequest = new KhachHangRequest();
        model.addAttribute("khachHang", khachHangRequest);
        return "admin/khach_hang/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("khachHang") KhachHangRequest khReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/khach_hang/create";
        }
        KhachHang khachHang = new KhachHang(null, khReq.getMa(), khReq.getTen(), khReq.getSdt(), khReq.getTrangThai());
        this.khachHangRepo.save(khachHang);
        return "redirect:/khach-hang/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ){
        khachHangRepo.deleteById(id);
        return "redirect:/khach-hang/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") KhachHang khachHang,
            Model model
    ){
        model.addAttribute("khachHang", khachHang);
        return "admin/khach_hang/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") KhachHang khachHang,
            @Valid @ModelAttribute("khachHang") KhachHangRequest khReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/khach_hang/edit";
        }
        khachHang.setMa(khReq.getMa());
        khachHang.setTen(khReq.getTen());
        khachHang.setSdt(khReq.getSdt());
        khachHang.setTrangThai(khReq.getTrangThai());
        khachHangRepo.save(khachHang);
        return "redirect:/khach-hang/show";
    }
}
