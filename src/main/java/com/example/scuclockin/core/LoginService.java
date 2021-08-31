package com.example.scuclockin.core;

import com.alibaba.fastjson.JSONObject;
import com.example.scuclockin.common.ClockErrorCode;
import com.example.scuclockin.common.ClockException;
import com.example.scuclockin.model.DataPack;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@Log4j2
@Service
public class LoginService {
    private static final String LOGIN_URL = "https://ua.scu.edu.cn/login";
    private static final String BASE_URL = "https://ua.scu.edu.cn";
    private static final String CAPTCHA_URI = "/captcha?captchaId=%s";
    private static final RestTemplate restTemplate = new RestTemplate();


    @Scheduled(fixedRate = 1000*100)
    public String getLoginUrl(){
        try {
            // 通过延迟2000毫秒然后再去请求可解决js异步加载获取不到数据的问题
            Document doc= Jsoup.connect(LOGIN_URL).timeout(2000).get();
            getExecution(doc);
            String id = getCaptchaId(doc);
            requestCaptcha(id);
            return "";
        }catch (Exception e){
            throw new ClockException(ClockErrorCode.FAILED_REQUEST_DATA);
        }
    }

    public static String getExecution(Document doc){
        Elements elements = doc.getElementsByAttributeValueMatching("name","execution");
        String execution = elements.get(0).attr("value");
        log.info(execution);
        return execution;
    }

    public static String getCaptchaId(Document doc){
        Elements scriptElements = doc.getElementsByTag("script");
        if (scriptElements == null && scriptElements.size() == 0){
            throw new RuntimeException();
        }
        Element e = scriptElements.get(3);
        if (e == null){
            throw new RuntimeException();
        }
        String[] s = e.toString().split(";");
        if (s == null && s.length == 0){
            throw new RuntimeException();
        }
        String[] s2 = s[2].split("=");
        if (s2 == null && s2.length == 0){
            throw new RuntimeException();
        }
        String json = s2[1];
        if (!StringUtils.hasLength(json)){
            throw new RuntimeException();
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = (String) jsonObject.get("id");
        log.info(id);
        return id;
    }

    @SneakyThrows
    public static void requestCaptcha(String id){
        String url = String.format(BASE_URL + CAPTCHA_URI, id);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        // 获取entity中的数据
        byte[] body = responseEntity.getBody();
        if (body == null){
            log.error("无法获取数据");
            throw new RuntimeException();
        }
        File dest = new File(id + ".png");
        if(!dest.exists()) {
            dest.createNewFile();
        }

        InputStream in = null;
        OutputStream out = null;

        in = new ByteArrayInputStream(body);
        out = new FileOutputStream(dest);

        byte []bt = new byte[1024];
        int length=0;
        while(	(length = in.read(bt)) !=-1) {
            out.write(bt,0,length);
        }
        if(null!=out) {
            out.close();
        }

    }
}
