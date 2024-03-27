package com.example.sd18203.controllers;

import com.example.sd18203.dto.MauSacRequest;
import com.example.sd18203.entities.MauSac;
import com.example.sd18203.repositories.MauSacRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("mau-sac")
public class MauSacController {
    @Autowired
    private MauSacRepository mauSacRepo;

    @GetMapping("show")
    public  String index(
            Model model,
            @RequestParam("page") Optional<Integer> pageParam
    ){
        Pageable pageable = PageRequest.of(pageParam.orElse(0), 3);
        Page<MauSac> pageMauSac = mauSacRepo.findByTrangThai(MauSacRepository.HOAT_DONG, pageable);
        model.addAttribute("pageMauSac", pageMauSac);
        System.out.println("List" + pageMauSac);
        return "admin/mau_sac/index";
    }

    @GetMapping("create")
    public  String create(Model model){
        MauSacRequest mauSacRequest = new MauSacRequest();
        model.addAttribute("mauSac", mauSacRequest);
        return "admin/mau_sac/create";
    }

    @PostMapping("store")
    public String store(
            @Valid @ModelAttribute("mauSac") MauSacRequest msReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/mau_sac/create";
        }
        MauSac mauSac = new MauSac(null, msReq.getMa(), msReq.getTen(), msReq.getTrangThai());
        mauSacRepo.save(mauSac);
        return "redirect:/mau-sac/show";
    }

    @GetMapping("delete/{id}")
    public String delete(
            @PathVariable("id") int id
    ){

        mauSacRepo.deleteById(id);
        return "redirect:/mau-sac/show";
    }


    @GetMapping("edit/{id}")
    public  String edit(
            @PathVariable("id") MauSac mauSac,
            Model model
    ){
        model.addAttribute("mauSac", mauSac);
        return "admin/mau_sac/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") MauSac mauSac,
            @Valid @ModelAttribute("mauSac") MauSacRequest msReq,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/mau_sac/edit";
        }
        mauSac.setMa(msReq.getMa());
        mauSac.setTen(msReq.getTen());
        mauSac.setTrangThai(msReq.getTrangThai());
        mauSacRepo.save(mauSac);
        return "redirect:/mau-sac/show";
    }
}
