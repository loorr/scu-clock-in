package com.example.scuclockin.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class PageController {

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

//    @PostMapping("/login")
//    public ModelAndView login(ModelAndView modelAndView, @Valid UserVo userVo, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
//            modelAndView.setViewName("login");
//            return modelAndView;
//        }
//        String userName = userVo.getUserName();
//        String password = userVo.getPassword();
//
//        if(!"admin".equals(userName)){
//            modelAndView.addObject("error","无此用户！");
//            modelAndView.setViewName("login");
//            return modelAndView;
//        }
//        if(!"123456".equals(password)){
//            modelAndView.addObject("error","密码错误！");
//            modelAndView.setViewName("login");
//            return modelAndView;
//        }
//        modelAndView.addObject("userName",userName);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}
