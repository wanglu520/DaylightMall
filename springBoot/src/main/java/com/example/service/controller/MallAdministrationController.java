package com.example.service.controller;

import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.serviceImpl.Impl.MallAdministrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mall")
public class MallAdministrationController {
    @Autowired(required = false)
    MallAdministrationImpl mallAdministration;

    @RequestMapping(value = "/queryRegion", method = RequestMethod.POST)
    public  OutPutObject queryRegion(){
        return mallAdministration.queryRegion();
    }
    @RequestMapping(value= "/queryBrand", method = RequestMethod.POST)
    public @ResponseBody OutPutObject queryBrand(@RequestBody Map body){
        return mallAdministration.queryBrand(body);
    }
    @RequestMapping(value= "/deleteBrand", method = RequestMethod.POST)
    public @ResponseBody OutPutObject deleteBrand(@RequestBody Map body){
        return mallAdministration.deleteBrand(body);
    }

}
