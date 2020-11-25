package com.jd.em.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigBeanValue {

    @Value("${sms.account}")
    public String account;

    @Value("${sms.pswd}")
    public String pswd;

    @Value("${sms.smsUrl}")
    public String smsUrl;

    @Value("${map.mapUrl}")
    public String mapUrl;
}