package com.example.scuclockin.common;

import com.example.common.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClockErrorCode implements BaseError {

    FAILED_REQUEST_DATA("1","Failed to request data"),
    EMAIL_SEND_FILED("2","邮件发送失败"),

    ;
    private String msg;
    private String code;

}
