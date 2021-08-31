package com.example.scuclockin.controller.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class GetCaptchaReq {
    @NotEmpty
    private String captchaId;
}
