package com.liuzg.jswebextra.plugins.pay.configure;


import com.liuzg.jswebextra.plugins.pay.sdk.tencent.IWXPayDomain;
import com.liuzg.jswebextra.plugins.pay.sdk.tencent.WXPayDomainSimpleImpl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WXPayConfigImpl extends WXPayConfig {

    private byte[] certData;
    private static WXPayConfigImpl INSTANCE;
    private static Map<String , String> configMap = new HashMap<>();
    public WXPayConfigImpl(){};
    public WXPayConfigImpl(String appid,String appsecret,String key,String mch_id,String certpath){
        configMap.put("appid",appid);
        configMap.put("appsecret",appsecret);
        configMap.put("key",key);
        configMap.put("mch_id",mch_id);
        configMap.put("certpath",certpath);
    }

    public static WXPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return configMap.get("appid")==null?"":configMap.get("appid");
    }

    public String getMchID() {
        return configMap.get("mch_id")==null?"":configMap.get("mch_id");
    }

    public String getKey() {
        return configMap.get("key")==null?"":configMap.get("key");
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

}
