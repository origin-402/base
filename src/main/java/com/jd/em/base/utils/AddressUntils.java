package com.jd.em.base.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.em.base.domain.PageData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.net.URL;
public class AddressUntils {
    private final static Logger logger = LoggerFactory.getLogger(AddressUntils.class);
    public static void main(String[] args) {
        // lat 31.2990170   纬度
        //log 121.3466440    经度
    	JSONObject add = AddressUntils.getAdd("121.3466440", "31.2990170");
        System.out.println(add.getString("result"));
 
    }
 
    /**
     *根据经纬度获取省市区
     * @param log
     * @param lat
     * @return
     */
    public static JSONObject getAdd(String log, String lat ){
    	PageData res = new PageData();
    	res.put("lon", log);
    	res.put("lat", lat);
    	res.put("ver", 1);
        String urlString = "http://api.tianditu.gov.cn/geocoder?postStr=[[postStr]]&type=geocode&tk=130df9e76012093a8c6d411bf434b7a7";
        String rep = HttpClient.doGet(urlString.replace("[[postStr]]", res.toString()));
        return JSON.parseObject(rep);
    }
 
 
}