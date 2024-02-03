package com.example.sd18203.controllers;

import com.example.sd18203.dto.KichThuocRequest;
import com.example.sd18203.entities.KichThuoc;
import com.example.sd18203.repositories.KichThuocRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("kich-thuoc")
public class KichThuocController {
    @Autowired
    private KichThuocRepository kichThuocRepo;

    @GetMapping("show")
    public String getKichThuocIntdex(Model model){
        model.addAttribute("listKichThuoc", kichThuocRepo.findAll());
        return "admin/kich_thuoc/index";
    }

    @GetMapping("create")
    public String getKichThuocCreate(Model model){
        KichThuocRequest kichThuocRequest = new KichThuocRequest();
        model.addAttribute("kichThuoc", kichThuocRequest);
        return "admin/kich_thuoc/create";
    }

    @PostMapping("store")
    public String kichThuocStore(
            @Valid @ModelAttribute("kichThuoc") KichThuocRequest ktReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "/admin/kich_thuoc/create";
        }
        KichThuoc kichThuoc = new KichThuoc(null, ktReq.getMa(), ktReq.getTen(), ktReq.getTrangThai());
        this.kichThuocRepo.save(kichThuoc);
        return "redirect:/kich-thuoc/show";
    }

    @GetMapping("delete/{id}")
    public String kichThuocDelete(
            @PathVariable("id") int id
    ){
        kichThuocRepo.deleteById(id);
        return "redirect:/kich-thuoc/show";
    }

    @GetMapping("edit/{id}")
    public String getKichThuocEdit(
            @PathVariable("id") KichThuoc kichThuoc,
            Model model
    ){
        model.addAttribute("kichThuoc", kichThuoc);
        return "admin/kich_thuoc/edit";
    }

    @PostMapping("update/{id}")
    public String kichThuocUpdate(
            @PathVariable("id") KichThuoc kichThuoc,
            @Valid @ModelAttribute("kichThuoc") KichThuocRequest ktReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/kich_thuoc/edit";
        }
        kichThuoc.setMa(ktReq.getMa());
        kichThuoc.setTen(ktReq.getTen());
        kichThuoc.setTrangThai(ktReq.getTrangThai());
        kichThuocRepo.save(kichThuoc);
        return "redirect:/kich-thuoc/show";
    }
}
