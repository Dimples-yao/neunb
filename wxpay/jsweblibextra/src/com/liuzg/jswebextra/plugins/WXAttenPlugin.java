package com.liuzg.jswebextra.plugins;

import com.liuzg.jswebextra.plugins.wxattention.model.WXUserData;
import com.liuzg.jswebextra.utils.HttpRequestUtils;
import com.liuzg.jswebextra.utils.WXAttenUtil;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class WXAttenPlugin{
    /**
     * 第一步 用户同意授权，获取code
     * 获取code的地址
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     * 附加的参数   response_type=code&scope=snsapi_base#wechat_redirect
     * scope=snsapi_userinfo   获取用户信息()
     */
    private static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * 第二步
     * 通过code换取网页授权access_token的url
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    private static final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 第三步：拉取用户信息(需scope为 snsapi_userinfo)
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    private static final String INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 该地址是为了获取用户是否关注过该公众号的信息，
     * 与上面的地址基本一样，只是传递的access-token不一样。获取到的用户信息中多了一个字段：是否关注公众号
     */
    private static final String INFO_URL_V2 = "https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * 获取access_token的url
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     */
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 获取JS_SDK 的 ticket
     * https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
     */
    public static final String JSTICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    /*
    * web登录授权地址
    */
    public static final String OPEN_URL_WEB="https://open.weixin.qq.com/connect/qrconnect";

    //新建一个用来存储微信配置信息的map变量
    private static Map<String , String> configMap = new HashMap<>();

    public WXAttenPlugin(){}

    public WXAttenPlugin(String appid,String appsecret,String openappid ,String openappsecret){
        configMap.put("appid",appid);
        configMap.put("appsecret",appsecret);
        configMap.put("openappid",openappid);
        configMap.put("openappsecret",openappsecret);
    }


    /**
     * 重定向到微信获取用户信息
     *
     * @param response
     * @param state    页面地址 域名后面的部分；此参数的目的是从哪个页面发起的登陆授权，授权完毕后再跳转回那个页面，若无此参数，则跳转回首页
     * @param redirect_uri 需要重定向的地址
     */
    public void getWebCharCode(HttpServletResponse response, String state,String redirect_uri) {

        String url = null;
        try {
            redirect_uri = URLEncoder.encode(redirect_uri,"utf-8");
            //第一步：用户同意授权，获取code
            url = CODE_URL + "?appid=APP&redirect_uri=REDIRECTURI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            url = url.replace("APP",configMap.get("appid")==null?"":configMap.get("appid"));
            url = url.replace("REDIRECTURI",redirect_uri);
            url = url.replace("STATE",state);
            //url = CODE_URL + "?appid=" + (configMap.get("appid")==null?"":configMap.get("appid")) + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_redirect";
            response.sendRedirect(url);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WXUserData getUserInfo(String code){
        WXUserData wxUserData = null;
        String url = null;
        url = OPENID_URL + "?appid=APP&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APP",configMap.get("appid")==null?"":configMap.get("appid"));
        url = url.replace("SECRET",configMap.get("appsecret")==null?"":configMap.get("appsecret"));
        url = url.replace("CODE",code);
        String resstr = HttpRequestUtils.sendGet(url);
        JSONObject jsonObject=new JSONObject(resstr);
        url = INFO_URL + "?access_token=ACCESSTOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESSTOKEN", (CharSequence) jsonObject.get("access_token"));
        url = url.replace("OPENID", (CharSequence) jsonObject.get("openid"));
        String user = HttpRequestUtils.sendGet(url);
        jsonObject=new JSONObject(user);
        wxUserData = WXAttenUtil.jsonTowxUserData(jsonObject);
        return wxUserData;
    }

    /**
    * web重定向到微信获取用户信息
     * @param state
     * @param redirect_uri 回调地址
    */
    public String getWebLoginurl (String state,String redirect_uri){
        String url = null;
        try {
            redirect_uri = URLEncoder.encode(redirect_uri,"utf-8");
            url = OPEN_URL_WEB + "?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
            url = url.replace("APPID",configMap.get("openappid")==null?"":configMap.get("openappid"));
            url = url.replace("REDIRECT_URI",redirect_uri);
            url = url.replace("STATE",state);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public WXUserData geWebtUserInfo(String code){
        WXUserData wxUserData = null;
        String url = null;
        url = OPENID_URL + "?appid=APP&secret=SECRET&code=CODE&grant_type=authorization_code";
        url = url.replace("APP",configMap.get("openappid")==null?"":configMap.get("openappid"));
        url = url.replace("SECRET",configMap.get("openappsecret")==null?"":configMap.get("openappsecret"));
        url = url.replace("CODE",code);
        String resstr = HttpRequestUtils.sendGet(url);
        JSONObject jsonObject=new JSONObject(resstr);
        url = INFO_URL + "?access_token=ACCESSTOKEN&openid=OPENID&lang=zh_CN";
        url = url.replace("ACCESSTOKEN", (CharSequence) jsonObject.get("access_token"));
        url = url.replace("OPENID", (CharSequence) jsonObject.get("openid"));
        String user = HttpRequestUtils.sendGet(url);
        jsonObject=new JSONObject(user);
        wxUserData = WXAttenUtil.jsonTowxUserData(jsonObject);
        return wxUserData;
    }

    /**
     * 将emoji表情替换成空串
     * @param src
     * @return 过滤后的字符串
     **/
    public static String filterEmoji(String src){
        if(src==null || "".equals(src))
            return "";
        return src.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
    }
}
