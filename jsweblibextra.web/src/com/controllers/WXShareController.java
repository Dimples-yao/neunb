package com.controllers;

import com.liuzg.jswebextra.plugins.WXAttenPlugin;
import com.liuzg.jswebextra.plugins.WXSharePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/wxshare")
public class WXShareController {
    @Autowired
    public WXShareController(@Qualifier("wxpay_config") Properties wxpayConfig) {
        new WXSharePlugin(wxpayConfig.getProperty("appid"),wxpayConfig.getProperty("appsecret"));
    }

    @RequestMapping("/doShare")
    @ResponseBody
    public Map<String, Object> doShare(String requestUrl){
        Map<String, Object> map = new HashMap<>();
        map = WXSharePlugin.doShaer(requestUrl);
        return map;
    }

}
