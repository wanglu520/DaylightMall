package com.example.service.controller;

import java.util.Map;

import com.example.service.OutPutObject;
import com.example.service.serviceImpl.UserAdministrationImpl.UserAdministrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAdmin")
public class UserAdministrationController{
    @Autowired(required=false)
    private UserAdministrationImpl userAdministrationImpl;

    @RequestMapping(value = "/queryUserDetail", method = RequestMethod.POST)
    public @ResponseBody OutPutObject userDetail(@RequestBody Map body){
        return userAdministrationImpl.userDetail(body);
    }
    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
    public @ResponseBody OutPutObject updateDetail(@RequestBody Map body){
        return userAdministrationImpl.updateDetail(body);
    }
    @RequestMapping(value = "/queryUserAddress", method = RequestMethod.POST)
    public @ResponseBody OutPutObject queryUserAddress(@RequestBody Map body){
        return userAdministrationImpl.queryUserAddress(body);
    }
}
