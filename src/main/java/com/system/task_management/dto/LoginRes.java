package com.system.task_management.dto;

public class LoginRes {
    private String token ;
    private String userName;


    public LoginRes(String userName, String token) {
        this.token = token;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }
}
