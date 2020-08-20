package com.example.service.dao;

import java.io.Serializable;

public class User implements Serializable {
    private Long user_id;//编号
    private String user_account;//用户名
    private String user_password;//密码
    private String user_phone;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
