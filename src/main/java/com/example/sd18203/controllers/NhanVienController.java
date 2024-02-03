package com.example.sd18203.controllers;

import com.example.sd18203.dto.KhachHangRequest;
import com.example.sd18203.dto.NhanVienRequest;
import com.example.sd18203.entities.NhanVien;
import com.example.sd18203.repositories.NhanVienRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienRepository nhanVienRepo;
    List<NhanVienRequest> listNhanVien = new ArrayList<>();

    public NhanVienController() {
        listNhanVien.add(new NhanVienRequest(1, "NV01", "Tuấn", "tuannv", "88888", 0));
        listNhanVien.add(new NhanVienRequest(2, "NV02", "Hưng", "hungnt", "88888", 0));
    }

    @GetMapping("show")
    public String getIndex(Model model){
        model.addAttribute("listNhanVien", nhanVienRepo.findAll());
        return "admin/nhan_vien/index";
    }

    @GetMapping("create")
    public String create(Model model){
        NhanVienRequest nhanVienRequest = new NhanVienRequest();
        model.addAttribute("nhanVien", nhanVienRequest);
        return "admin/nhan_vien/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("nhanVien") NhanVienRequest nvReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/nhan_vien/create";
        }
        NhanVien nhanVien = new NhanVien(null, nvReq.getMa(), nvReq.getTen(), nvReq.getTenDangNhap(), nvReq.getMatKhau(), nvReq.getTrangThai());
        nhanVienRepo.save(nhanVien);
        return "redirect:/nhan-vien/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ){
        nhanVienRepo.deleteById(id);
        return "redirect:/nhan-vien/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") NhanVien nhanVien,
            Model model
    ){
        model.addAttribute("nhanVien", nhanVien);
        return "admin/nhan_vien/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") NhanVien nhanVien,
            @Valid @ModelAttribute("nhanVien") NhanVienRequest nvReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/nhan_vien/edit";
        }
        nhanVien.setMa(nvReq.getMa());
        nhanVien.setTen(nvReq.getTen());
        nhanVien.setTenDangNhap(nvReq.getTenDangNhap());
        nhanVien.setMatKhau(nvReq.getMatKhau());
        nhanVien.setTrangThai(nvReq.getTrangThai());
        nhanVienRepo.save(nhanVien);
        return "redirect:/nhan-vien/show";
    }
}
