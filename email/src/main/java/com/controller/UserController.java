package com.controller;

import com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String checkLogin(String useraddress,String passwd ,HttpServletRequest request){
//        log.info("用户登录");
        HttpSession session = request.getSession();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        List<Map<String , Object>> checkLogin = userService.checkLogin(useraddress,passwd);
        if (checkLogin.size() != 0){
            session.setAttribute("userid",checkLogin.get(0).get("userid"));
            session.setAttribute("useraddress",checkLogin.get(0).get("useraddress"));
            return "index";
        }else {
            return "login";
        }
    }

    @RequestMapping("/getUserMessage")
    @ResponseBody
    public Map<String , Object> getUserMessage(HttpServletRequest request){
//        log.info("查询该用户的所有信息");
        HttpSession session = request.getSession();
        String  userid = session.getAttribute("userid")== null ? "0" :session.getAttribute("userid").toString();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap = userService.getUserMessage(userid);
        return resultMap;
    }

}
