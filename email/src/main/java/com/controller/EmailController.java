package com.controller;

import com.service.EmailService;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by NEUNB_Lisy on 2017/7/19.
 */
@Controller
@RequestMapping("/email")
public class EmailController {

    private Logger log = Logger.getLogger(EmailController.class);

    @Resource
    private EmailService emailService;

    WebSocket webSocket = new WebSocket();

    //当MyWebSocketHandler类被加载时就会创建该Map，随类而生


    @RequestMapping(value = "/loademail" )
    @ResponseBody
    public Map<String , Object> loademail (HttpServletRequest request,String pageNow){
//        log.info("加载用户收件箱");
        HttpSession session = request.getSession();
        int emailCount;//记录用户收件箱的数量
        List<Map<String ,Object>> emails = new ArrayList<Map<String , Object>>();//记录用户未删除的收件箱
        Map<String ,Object> map = null;
        Map<String ,Object> result = new HashMap<>();
        String userid = session.getAttribute("userid")== null ? "0" :session.getAttribute("userid").toString();
        if (userid.equals("0")){
            map = new HashMap<String, Object>();
            map.put("gologin","login");
            emails.add(map);
            result.put("emails",emails);
            return result;
        }else {
            emailCount = emailService.getEmailCount(Integer.parseInt(userid));
            emails = emailService.loadEmail(Integer.parseInt(userid),Integer.parseInt(pageNow));
            result.put("emails",emails);
            result.put("emailCount",emailCount);
            return result;
        }
    }

    @RequestMapping(value = "/sendEmail")
    @ResponseBody
    public void sendEmail (String title,String content,String senderaddress,String acceptoraddress,HttpServletRequest request){
//        log.info("发送邮件");
        if(!title.equals(null) && !content.equals(null) && !senderaddress.equals(null) && !acceptoraddress.equals(null) ){
            //将邮件写入数据库
            Date date = new Date();
            Timestamp timeStamep = new Timestamp(date.getTime());
            String emaildata = timeStamep.toString();
            emailService.sendEmail(title,content,emaildata,senderaddress,acceptoraddress);

            HttpSession session = request.getSession();
            //发送socket
            webSocket.sendMsg(acceptoraddress,session);
        }
    }


}
