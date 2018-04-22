package com.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/7/19.
 */
public interface EmailService {

    //查看用户收件箱数量
    int getEmailCount(@Param("userid") int userid);

    //加载用户收件箱
    List<Map<String , Object>> loadEmail(@Param("userid") int userid,@Param("pageNow") int pageNow);

    //发送邮件
    void sendEmail(@Param("title") String title, @Param("content") String content, @Param("emaildata") String emaildata, @Param("senderaddress") String senderaddress, @Param("acceptoraddress") String acceptoraddress);

}
