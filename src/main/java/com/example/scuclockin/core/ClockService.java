package com.example.scuclockin.core;

import com.alibaba.fastjson.JSONObject;
import com.example.scuclockin.model.DataPack;
import com.example.scuclockin.model.ResponseTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ClockService {
    private static final String COOKIES = "_ga=GA1.3.1861943163.1626960281; " +
            "eai-sess=ann60quh7hltgh53dgq2kurl94; UUkey=a20ac0661d05ca0de9a816912996aaf0;" +
            " Hm_lvt_48b682d4885d22a90111e46b972e3268=1627832135;" +
            " Hm_lpvt_48b682d4885d22a90111e46b972e3268=1627834554";
    private static final String EMAIL = "599390673@qq.com";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String SAVE_URL = "https://wfw.scu.edu.cn/ncov/wap/default/save";

    @Resource
    private MailService mailService;

    // @Scheduled(cron = "0 5 */24 * * *")
    @Scheduled(cron = "30 30 0 * * ?")
    public void test(){
        HttpHeaders headers = getHeader(COOKIES);
        MultiValueMap<String, Object> postParams = new DataPack().toMultiValueMap();
        HttpEntity<MultiValueMap> entity = new HttpEntity<>(postParams,headers);
        try {
            JSONObject rep = restTemplate.postForObject(SAVE_URL, entity, JSONObject.class);
            ResponseTemplate result = rep.toJavaObject(ResponseTemplate.class);
            if (result != null && "操作成功".equals(result.getM())){
                mailService.succHandler("今日已打卡成功");
                return;
            }
            if (result != null && "今天已经填报了".equals(result.getM())){
                mailService.succHandler("今天已经填报了");
            }
            mailService.failHandler(result.getM());
        }catch (Exception e){
            mailService.failHandler("打卡失败");
        }
    }

    private HttpHeaders getHeader(String cookie){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json, text/javascript, */*; q=0.01");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add(HttpHeaders.COOKIE, cookie);
        headers.add(HttpHeaders.HOST, "wfw.scu.edu.cn");
        headers.add(HttpHeaders.ORIGIN, "https://wfw.scu.edu.cn");
        headers.add(HttpHeaders.REFERER, "https://wfw.scu.edu.cn/ncov/wap/default/index");
        headers.add("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"");
        headers.add("sec-ch-ua-mobile", "?0");
        headers.add("Sec-Fetch-Dest", "empty");
        headers.add("Sec-Fetch-Mode", "cors");
        headers.add("Sec-Fetch-Site", "same-origin");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        headers.add("X-Requested-With", "XMLHttpRequest");
        return headers;
    }
}
