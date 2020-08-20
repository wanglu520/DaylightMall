package com.example.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
//统一返回数据格式
@Repository
public class OutPutObject implements Serializable {
    private static final long serialVersionUID = 3725649046703751656L;
    private String returnCode;
    private String returnMessage;
    private Map<String, Object> bean;
    private List<Map<String, Object>> beans;
    public OutPutObject(){}
    public OutPutObject(String returnCode){
        this.setReturnCode(returnCode);
    }
    public OutPutObject(String returnCode, String returnMessage){
        this.setReturnCode(returnCode);
        this.setReturnMessage(returnMessage);
    }
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Map<String, Object> getBean() {
        return bean;
    }

    public void setBean(Map<String, Object> bean) {
        this.bean = bean;
    }

    public List<Map<String, Object>> getBeans() {
        return beans;
    }

    public void setBeans(List<Map<String, Object>> beans) {
        this.beans = beans;
    }

}
