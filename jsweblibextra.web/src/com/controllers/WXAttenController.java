package com.controllers;


import com.liuzg.jswebextra.plugins.WXAttenPlugin;
import com.liuzg.jswebextra.plugins.pay.configure.WXPayConfigImpl;
import com.liuzg.jswebextra.plugins.wxattention.model.WXUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/webchat")
public class WXAttenController {

    private WXAttenPlugin wxAttenPlugin = null;

    @Autowired
    public WXAttenController(@Qualifier("wxpay_config") Properties wxpayConfig) {
        new WXAttenPlugin(wxpayConfig.getProperty("appid"),wxpayConfig.getProperty("appsecret"),wxpayConfig.getProperty("openappid"),wxpayConfig.getProperty("openappsecret"));
    }

    /**
     * 重定向到微信获取用户信息
     *
     * @param request
     * @param response
     * @param state    页面地址 域名后面的部分；此参数的目的是从哪个页面发起的登陆授权，授权完毕后再跳转回那个页面，若无此参数，则跳转回首页
     * @param redirect_uri 需要重定向的地址
     */
    @RequestMapping("/getinfo")
    @ResponseBody
    public String getWebCharCode( String state,String redirect_uri ,HttpServletRequest request, HttpServletResponse response) {
        try {
            wxAttenPlugin = new WXAttenPlugin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wxAttenPlugin.getWebCharCode(response,state,redirect_uri);
        return "success";
    }

    /**
     * 获取允许授权的用户信息
     * @param code  code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public WXUserData getUserInfo(String code,String state) {
        try {
            wxAttenPlugin = new WXAttenPlugin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXUserData wxUserData = new WXUserData();
        wxUserData = wxAttenPlugin.getUserInfo(code);
        return wxUserData;
        //TODO:得到需要关注的用户信息  以下为wxUserData的参数说明
        //openid	用户的唯一标识
        //nickname	用户昵称
        //sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        //province	用户个人资料填写的省份
        //city	普通用户个人资料填写的城市
        //country	国家，如中国为CN
        //headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
        //privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
        //unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    }

    /**
    * pc端授权登录
    * @param state
     * @param redirect_uri 回调地址
    */
    @RequestMapping("/getpcWxloginUrl")
    @ResponseBody
    public Map<String,String> getpcWxloginUrl(String state, String redirect_uri){
        Map<String,String> map = new HashMap<>();
        try {
            wxAttenPlugin = new WXAttenPlugin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("result","success");
        map.put("url",wxAttenPlugin.getWebLoginurl(state,redirect_uri));
        return map;
    }

    /**
     * 获取允许授权的用户信息
     * @param code  code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     */
    @RequestMapping("/geWebtUserInfo")
    @ResponseBody
    public WXUserData geWebtUserInfo(String code,String state) {
        try {
            wxAttenPlugin = new WXAttenPlugin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXUserData wxUserData = new WXUserData();
        wxUserData = wxAttenPlugin.geWebtUserInfo(code);
        return wxUserData;
        //TODO:得到需要关注的用户信息  以下为wxUserData的参数说明
        //openid	用户的唯一标识
        //nickname	用户昵称
        //sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        //province	用户个人资料填写的省份
        //city	普通用户个人资料填写的城市
        //country	国家，如中国为CN
        //headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
        //privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
        //unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
    }


}
