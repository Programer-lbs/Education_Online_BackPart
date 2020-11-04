package org.edu.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class WeiXinConstant implements InitializingBean {

    @Value("${weixin.open.app_id}")
    private String appId;
    @Value("${weixin.open.app_secret}")
    private String appSecret;
    @Value("${weixin.open.redirect_url}")
    private String redirectUrl;

    public static  String APP_ID ;
    public static  String APP_SECRET;
    public static  String REDIRECT_URL;


    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        REDIRECT_URL = redirectUrl;
    }
}
