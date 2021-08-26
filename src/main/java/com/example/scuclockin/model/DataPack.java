package com.example.scuclockin.model;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
public class DataPack {
    /** 今日是否在中高风险地区？（中高风险地区信息可通过国务院客户端小程序实时查询）["0"] */
    private String zgfxdq = "0";
    /** 今日是否接触密接人员？*/
    private String mjry = "0";
    /** 近14日内本人/共同居住者是否去过疫情发生场所（市场、单位、小区等）或与场所人员有过密切接触？*/
    private String csmjry= "0";
    /** 所在校区*/
    private String szxqmc = "江安校区";
    /** 是否接种过新冠疫苗？ */
    private String sfjzxgym = "1";
    /** 接种第一剂新冠疫苗时间 */
    private String  jzxgymrq = "2021-05-12";
    /** 是否接种第二剂新冠疫苗？*/
    private String sfjzdezxgym = "1";
    /** 接种第二剂新冠疫苗时间*/
    private String jzdezxgymrq = "2021-06-24";
    /** 今日体温范围 36.6℃-36.9℃*/
    private String tw = "3";
    /** 今日是否出现发热、乏力、干咳、呼吸困难等症状？*/
    private String sfcxtz = "0";
    /** 今日是否接触无症状感染/疑似/确诊人群？*/
    private String sfjcbh = "0";
    /** 是否有任何与疫情相关的， 值得注意的情况？*/
    private String sfcxzysx = "0";
    /** 情况说明 */
    private String qksm = "无";
    /** 是否到相关医院或门诊检查 */
    private String sfyyjc = "0";
    /** 检查结果属于以下哪种情况 */
    private String jcjgqr = "0";
    /** 其他信息 */
    private String remark = null;
    /** 地址 */
    private String address = "四川省成都市双流区西航港街道四川大学江安校区";
    private String geo_api_info = getLocation();
    private String area = "四川省 成都市 双流区";
    private String province="四川省";
    private String city = "成都市";
    /** 今日是否在校？*/
    private String sfzx = "1";
    private String sfjcwhry = "0";
    private String sfjchbry = "0";
    /** 否处于观察期？*/
    private String sfcyglq = "0";
    /** 观察场所*/
    private String gllx =null;
    /** 观察开始时间*/
    private String glksrq =null;
    /** 接触人群类型*/
    private String jcbhlx =null;
    private String jcbhrq =null;
    /** 是否移动*/
    private String ismoved = "0";
    private String bztcyy =null;
    private String sftjhb = "0";
    private String sftjwh = "0";
    private String szcs =null;
    private String szgj =null;
    private String bzxyy =null;
    private String szgjcs =null;
    private String jcjg =null;
    private String hsjcrq =null;
    private String hsjcdd =null;
    private String hsjcjg = "0";

    private String getLocation(){
        String s ="{\"type\":\"complete\",\"position\":{\"Q\":30.557918873211168,\"R\":104.00333700391711," +
                "\"lng\":104.003353,\"lat\":30.557441},\"location_type\":\"html5\",\"message\":\"" +
                "Get ipLocation failed.Get geolocation success.Convert Success.Get address success.\"," +
                "\"accuracy\":20185,\"isConverted\":true,\"status\":1,\"addressComponent\":{\"citycode\":" +
                "\"028\",\"adcode\":\"510116\",\"businessAreas\":[{\"location\":\"104.006821,30.562482\"," +
                "\"name\":\"白家\",\"id\":\"510116\"}],\"neighborhoodType\":\"\",\"neighborhood\":{\"name\":\"四川大学江安校区\"," +
                "\"type\":\"科教文化服务;学校;高等院校\"},\"building\":{\"name\":[],\"type\":[]},\"buildingType\":\"\"," +
                "\"street\":\"川大路一段\",\"streetNumber\":\"28号\",\"country\":\"中国\",\"province\":\"四川省\"," +
                "\"city\":\"成都市\",\"district\":\"双流区\",\"township\":\"510116002000\"}," +
                "\"formattedAddress\":\"四川省成都市双流区西航港街道四川大学江安校区\",\"roads\":[],\"crosses\":[],\"pois\":[],\"info\":\"SUCCESS\"}";
        return s;
    }


    @SneakyThrows
    public MultiValueMap<String, Object> toMultiValueMap(){
        MultiValueMap<String, Object> postParams = new LinkedMultiValueMap<>();
        Field[] fields=DataPack.class.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            String attributeName=fields[i].getName();
            //将属性名的首字母变为大写，为执行set/get方法做准备
            String methodName=attributeName.substring(0,1).toUpperCase()+attributeName.substring(1);
            Object result;
            Method getMethod=DataPack.class.getMethod("get"+methodName);
            //执行该set方法
            result=getMethod.invoke(this);
            postParams.add(attributeName, result);
        }
        return postParams;
    }
}
