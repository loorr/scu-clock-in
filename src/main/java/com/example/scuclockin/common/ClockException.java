package com.example.scuclockin.common;

import com.example.common.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ClockException extends RuntimeException implements BaseError {
    private String code;
    private String msg;

    public ClockException(BaseError e){
        this.code = e.getCode();
        this.msg = e.getMsg();
    }
}
