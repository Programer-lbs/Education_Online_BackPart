package org.edu.controller;

import io.swagger.annotations.Api;
import org.edu.util.WeiXinConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Api("使用微信登录")
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxLoginController {

    @GetMapping("/login")
    public String getWxCode(){
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect"+
                "?appid=%s"+
                "&redirect_uri=%s"+
                "&response_type=code"+
                "&scope=snsapi_login"+
                "&state=%s"+
                "#wechat_redirec";
        String redirectUrl = WeiXinConstant.REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String url = String.format(baseUrl,
                WeiXinConstant.APP_ID,
                redirectUrl,
                "wechat");

        return "redirect:"+url;
    }

    @GetMapping("/callback")
    public String callback(String code,String state){
        System.out.println(code);
        System.out.println(state);
        return "redirect:kkkk";
    }
}
