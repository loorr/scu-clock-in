package com.example.scuclockin.core;

import com.example.common.exception.AuthExcetption;
import com.example.scuclockin.common.ClockErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;


@Slf4j
@Component
public class MailService {
    private static final String ENCODING =  "GB2312";

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Async // 3s
    public void sendMail(String to, String subject, String context){
        if(!StringUtils.hasLength(to)){
            throw new RuntimeException("没有邮件接受者");
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true, ENCODING);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(context);
            messageHelper.setTo(to);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.error("邮件发送失败 {}", to);
            e.printStackTrace();
        }
    }
}
