package com.example.demo.controller;

import com.example.demo.pojo.CustomResult;
import com.example.demo.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@RestController


public class LoginController {
    @GetMapping("/api/login")
    public CustomResult Login(String code) throws IOException {
        //GET https://api.weixin.qq.com/sns/jscode2session   ?appid=APPID & secret=SECRET & js_code=JSCODE  & grant_type=authorization_code
        System.out.println("code:"+code);
        String urlHeader = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> urlParams = new HashMap<>();
        String APPID ="wx8854d7526fa4420c";
        String APP_SECRET ="feff06df4ee50729a91b7d5c64c4e22e";

        urlParams.put("appid",APPID);
        urlParams.put("secret",APP_SECRET);
        urlParams.put("js_code",code);
        urlParams.put("grant_type","authorization_code");
        String httpResult = HttpUtils.getResponse(urlHeader,urlParams);
        //System.out.println(httpResult);

        return new CustomResult(httpResult);
    }
}
