package com.liuzg.jswebextra.plugins;

import com.liuzg.jswebextra.plugins.sendmsg.model.MsgResult;
import com.liuzg.jswebextra.plugins.sendmsg.model.Template;
import com.liuzg.jswebextra.utils.CommonUtil;
import net.sf.json.JSONObject;

public class WXSendMsgPlugin {

    /**
     * 消息模板向用户发送消息
     * @param template 发送内容
     */
    public static MsgResult sendTemplateMsg(Template template){



        //存储微信返回结果
        MsgResult msgResult = new MsgResult();

        //获取access_token
        WXAccesstokenPlugin wxAccesstokenPlugin = new WXAccesstokenPlugin();
        String token = wxAccesstokenPlugin.getAccesstoken();

        //向用户发送消息
        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);

        //json存储微信返回信息
        JSONObject jsonResult= CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());

        msgResult.setErrcode(String.valueOf(jsonResult.get("errcode")));
        msgResult.setErrmsg(String.valueOf(jsonResult.get("errmsg")));
        msgResult.setMsgid(String.valueOf(jsonResult.get("msgid")));
        return msgResult;
    }
}
