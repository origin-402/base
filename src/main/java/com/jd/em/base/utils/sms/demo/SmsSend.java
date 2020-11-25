package com.jd.em.base.utils.sms.demo;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.em.base.config.ConfigBeanValue;
import com.jd.em.base.utils.sms.request.SmsSendRequest;
import com.jd.em.base.utils.sms.response.SmsSendResponse;
import com.jd.em.base.utils.sms.util.ChuangLanSmsUtil;

/**
 *
 * @author tianyh
 * @Description:普通短信发送
 */
public class SmsSend {

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N415663_N1574312";
    // 用户平台API密码(非登录密码)2018-07-20 14:35:54
    public static String pswd = "nKwqHkxbdC6100";
    //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
    public static String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";



/*
    public static void main(String[] args) throws UnsupportedEncodingException {
          // 短信内容
        String msg = "【美杰物业】尊敬的李学鹏业主您好：截止2018-07-20 14:39:53，您已产生各项费用253.20元，请您及时到物业进行缴纳。";
        //手机号码
        String phone = "15653253155";
        JSONObject res = sendMsg(msg,phone,conf);
        System.out.println(res.get("errorMsg"));

    }
*/

    public static JSONObject sendMsg(String msg, String phone, ConfigBeanValue conf) {
        SmsSendRequest smsSingleRequest = new SmsSendRequest(conf.account, conf.pswd, msg, phone,"true");
        String response = ChuangLanSmsUtil.sendSmsByPost(conf.smsUrl, JSON.toJSONString(smsSingleRequest));
        return JSON.parseObject(response);
    }

}
