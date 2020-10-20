package com.example.service.controller;

import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.serviceImpl.GoodsImpl.GoodsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired(required = false)
    GoodsImpl goods;

    @RequestMapping(value = "/queryGoods", method = RequestMethod.POST)
    public @ResponseBody OutPutObject queryGoods(@RequestBody Map body){
        return goods.queryGoods(body);
    }
}