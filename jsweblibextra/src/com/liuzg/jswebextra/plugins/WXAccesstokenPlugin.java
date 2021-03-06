package com.liuzg.jswebextra.plugins;

import com.liuzg.jswebextra.utils.HttpRequestUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/12/18.
 */
public class WXAccesstokenPlugin {

    //新建一个用来存储微信配置信息的map变量
    private static Map<String , String> configMap = new HashMap<>();

    public WXAccesstokenPlugin() {
    }

    public WXAccesstokenPlugin(String appid, String appsecret) {
        configMap.put("appid",appid);
        configMap.put("appsecret",appsecret);
    }

    public String getAccesstoken(){
        String url = null;
        url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        url = url.replace("APPID",configMap.get("appid")==null?"":configMap.get("appid"));
        url = url.replace("APPSECRET",configMap.get("appsecret")==null?"":configMap.get("appsecret"));
        String result = HttpRequestUtils.sendGet(url);
        JSONObject jsonObject=new JSONObject(result);
        String accesstoken = String.valueOf(jsonObject.get("access_token"));
        return accesstoken;
    }
}
