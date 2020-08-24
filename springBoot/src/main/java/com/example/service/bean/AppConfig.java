package com.example.service.bean;

import com.example.service.Utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public JwtUtils jwtUtils(){
        System.out.println("99999999999999999999999999999999999999999999999999999999999999999999999999999999");
        return new JwtUtils();
    }
}
