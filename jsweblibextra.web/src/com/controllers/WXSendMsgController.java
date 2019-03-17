package com.controllers;

import com.liuzg.jswebextra.plugins.WXAccesstokenPlugin;
import com.liuzg.jswebextra.plugins.WXSendMsgPlugin;
import com.liuzg.jswebextra.plugins.sendmsg.model.MsgResult;
import com.liuzg.jswebextra.plugins.sendmsg.model.Template;
import com.liuzg.jswebextra.plugins.sendmsg.model.TemplateParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/wxendmsg")
public class WXSendMsgController {

    //读取配置文件
    @Autowired
    public WXSendMsgController(@Qualifier("wxpay_config") Properties wxpayConfig){
        new WXAccesstokenPlugin(wxpayConfig.getProperty("appid"),wxpayConfig.getProperty("appsecret"));
    }

    /**
     * 消息模板向用户发送消息
     * @param userId 用户对于微信公众平台的openid
     * @param url 该消息的链接
     * @param first 标题
     * @param keyword1 慈善项目名称
     * @param keyword2 捐款金额
     * @param keyword3 捐款时间
     * @param remark 简介
     * @return msgResult
     */
    @RequestMapping("/sendmsg")
    @ResponseBody
    public MsgResult sendTemplateMsg(String userId,String url,String first,String keyword1,String keyword2,String keyword3,String remark){
        //返回结果
        MsgResult msgResult = new MsgResult();

        //发送用户信息
        Template tem=new Template();
        //模板ID
        tem.setTemplateId("z4o1Dem7pwPi2zz0i6LvceGBd5X55_PeDw77C-BGwgU");
        tem.setTopColor("#00DD00");
        //用户ID
        tem.setToUser(userId);
        //连接
        tem.setUrl(url);

        //发送内容
        List<TemplateParam> paras=new ArrayList<TemplateParam>();
        //发送标题
        paras.add(new TemplateParam("first",first,"#FF3333"));
        paras.add(new TemplateParam("keyword1",keyword1,"#0044BB"));
        paras.add(new TemplateParam("keyword2",keyword2,"#0044BB"));
        paras.add(new TemplateParam("keyword3",keyword3,"#AAAAAA"));
        paras.add(new TemplateParam("remark",remark,"#AAAAAA"));

        tem.setTemplateParamList(paras);

        //调用接口，向用户发送消息
        msgResult= WXSendMsgPlugin.sendTemplateMsg(tem);
        return msgResult;
    }

}
