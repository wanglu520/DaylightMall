package com.example.service.serviceImpl.Impl;

import com.example.service.OutPutObject;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl {
    private static final String successCode = "0"; //成功
    private static final String failCode = "-9999";//失败
    @Autowired
    private OutPutObject outPutObject;

    public OutPutObject getOutPutObject(){
        return new OutPutObject(successCode);
    }

    public OutPutObject getOutPutObject(String returnCode){
        return new OutPutObject(returnCode);
    }

    public OutPutObject getOutPutObject(String returnCode, String returnMessage){
        return new OutPutObject(returnCode, returnMessage);
    }
}
