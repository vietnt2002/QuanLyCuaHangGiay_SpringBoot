package com.example.sd18203.controllers;

import com.example.sd18203.dto.SanPhamRequest;
import com.example.sd18203.entities.SanPham;
import com.example.sd18203.repositories.SanPhamRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamRepository sanPhamRepo;

    @GetMapping("show")
    public String show(Model model){
        model.addAttribute("listSanPham", sanPhamRepo.findAll());
        return "admin/san_pham/index";
    }

    @GetMapping("create")
    public String create(Model model){
        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        model.addAttribute("sanPham", sanPhamRequest);
        return "admin/san_pham/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("sanPham") SanPhamRequest spReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/san_pham/create";
        }
        SanPham sanPham = new SanPham(null, spReq.getMa(), spReq.getTen(), spReq.getTrangThai());
        sanPhamRepo.save(sanPham);
        return "redirect:/san-pham/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ){
        sanPhamRepo.deleteById(id);
        return "redirect:/san-pham/show";
    }

    @GetMapping("edit/{id}")
    public String edit(
            @PathVariable("id") SanPham sanPham,
            Model model
    ){

        model.addAttribute("sanPham", sanPham);
        return "admin/san_pham/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") SanPham sanPham,
            @Valid @ModelAttribute("sanPham") SanPhamRequest spReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/san_pham/edit";
        }
        sanPham.setMa(spReq.getMa());
        sanPham.setTen(spReq.getTen());
        sanPham.setTrangThai(spReq.getTrangThai());
        sanPhamRepo.save(sanPham);
        return "redirect:/san-pham/show";
    }
}
