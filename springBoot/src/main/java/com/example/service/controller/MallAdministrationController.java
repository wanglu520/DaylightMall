package com.example.service.controller;

import java.io.IOException;
import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.serviceImpl.MallAdministrationImpl.MallAdministrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public OutPutObject queryBrand(@RequestBody Map body){
        return mallAdministration.queryBrand(body);
    }
    @RequestMapping(value= "/deleteBrand", method = RequestMethod.POST)
    public OutPutObject deleteBrand(@RequestBody Map body){
        return mallAdministration.deleteBrand(body);
    }
    @RequestMapping(value= "/updateBrand", method = RequestMethod.POST)
    public OutPutObject updateBrand(@RequestBody Map body){
        return mallAdministration.updateBrand(body);
    }
    @RequestMapping(value= "/addBrand", method = RequestMethod.POST)
    public OutPutObject addBrand(@RequestBody Map body){
        return mallAdministration.addBrand(body);
    }

    @PostMapping("/uploadPhoto")
    public OutPutObject uploadPhoto(@RequestParam("file") MultipartFile file)throws IOException {
        return mallAdministration.uploadPhoto(file);
    }

}
