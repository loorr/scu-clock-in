package com.example.scuclockin.model;

import lombok.Data;

@Data
public class LoginVo {
    public enum LoginType{
        username_smstoken,
        username_password;
    }
    private String username;
    private String password;
    private String captcha;
    private String execution;
    private String submit = "登录";
    private LoginType type ;
    private String _eventId = "submit";
    // 登录不需要携带
    private String captchaId;

}
