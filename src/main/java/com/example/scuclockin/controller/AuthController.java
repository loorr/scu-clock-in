package com.example.scuclockin.controller;


import com.example.scuclockin.controller.request.GetCaptchaReq;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;


@Api
@RestController
@RequestMapping("/auth")
public class AuthController {

    @SneakyThrows
    @PostMapping(value = "/get-captcha",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getCaptcha(@Valid GetCaptchaReq request){
        String fileName = request.getCaptchaId() + ".png";
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes, 0, fileInputStream.available());
        return bytes;
    }

}
