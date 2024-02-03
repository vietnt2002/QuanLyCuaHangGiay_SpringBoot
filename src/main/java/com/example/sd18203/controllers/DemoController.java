package com.example.sd18203.controllers;

import com.example.sd18203.dto.LoginRequest;
import com.example.sd18203.dto.MauSacRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    @Autowired
    @Qualifier("trang")
    private MauSacRequest msReq;

    @RequestMapping("hien-thi")
    public String hello(){
        System.out.println(msReq.getId());
        System.out.println(msReq.getMa());
        System.out.println(msReq.getTen());
        return "hello";
    }






    @GetMapping("dang-nhap-form")
    public String getDangNhap(Model model){
        return "dangNhap";
    }

    @PostMapping("dang-nhap")
    public String dangNhap(
//            @RequestParam("email") String username,
//            @RequestParam("password") String password,
            LoginRequest req,
            Model model
    ){
        System.out.println("-------------------");
        System.out.println(req.getEmail());
        System.out.println(req.getPassword());
        model.addAttribute("name", req.getEmail());
        return "dangNhap";
    }
}
