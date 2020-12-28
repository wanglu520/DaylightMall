package com.example.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/index")
    /*@GetMapping("/index")
    @ResponseBody*/
    public String sayHello(){
        return "index";
    }
}
