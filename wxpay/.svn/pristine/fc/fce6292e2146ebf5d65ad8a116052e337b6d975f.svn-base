package com.liuzg.jswebextra.utils;

import com.liuzg.jswebextra.plugins.wxattention.model.WXUserData;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/10/30.
 */
public class WXAttenUtil {
    public static WXUserData jsonTowxUserData(JSONObject jsonObject){
        WXUserData wxUserData = new WXUserData();
        wxUserData.setOpenid(String.valueOf(jsonObject.has("openid")?jsonObject.get("openid"):""));
        wxUserData.setNickname(String.valueOf(jsonObject.has("nickname")?jsonObject.get("nickname"):""));
        wxUserData.setSex(String.valueOf(jsonObject.has("sex")?jsonObject.get("sex"):""));
        wxUserData.setLanguage(String.valueOf(jsonObject.has("language")?jsonObject.get("language"):""));
        wxUserData.setCity(String.valueOf(jsonObject.has("city")?jsonObject.get("city"):""));
        wxUserData.setProvince(String.valueOf(jsonObject.has("province")?jsonObject.get("province"):""));
        wxUserData.setCountry(String.valueOf(jsonObject.has("country")?jsonObject.get("country"):""));
        wxUserData.setHeadimgurl(String.valueOf(jsonObject.has("headimgurl")?jsonObject.get("headimgurl"):""));
        wxUserData.setPrivilege(String.valueOf(jsonObject.has("privilege")?jsonObject.get("privilege"):""));
        wxUserData.setUnionid(String.valueOf(jsonObject.has("unionid")?jsonObject.get("unionid"):""));
        wxUserData.setAccess_token(String.valueOf(jsonObject.has("access_token")?jsonObject.get("access_token"):""));
        return wxUserData;
    }
}
