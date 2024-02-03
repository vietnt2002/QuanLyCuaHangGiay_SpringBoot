package com.example.sd18203.config;

import com.example.sd18203.dto.MauSacRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("trang")
    public MauSacRequest configBean1(){
        MauSacRequest ms = new MauSacRequest(1, "MS01", "Trắng", 0);
        return ms;
    }


    @Bean("den")
    public MauSacRequest configBean2(){
        MauSacRequest ms1 = new MauSacRequest(2, "MS02", "Đen", 0);
        return ms1;
    }
}
