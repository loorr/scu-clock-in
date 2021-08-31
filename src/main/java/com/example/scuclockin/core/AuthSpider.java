package com.example.scuclockin.core;

import com.alibaba.fastjson.JSONObject;
import com.example.scuclockin.model.LoginVo;

public class AuthSpider {
    public static void main(String[] args) {
        LoginVo loginVo = new LoginVo();
        loginVo.setType(LoginVo.LoginType.username_password);
        System.out.println(JSONObject.toJSON(loginVo));
    }
}
