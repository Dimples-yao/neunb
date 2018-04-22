package com.service.impl;

import com.dao.EmailDao;
import com.service.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/7/19.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmailServicelmpl implements EmailService{

    @Resource
    private EmailDao emailDao;

    //查看收件箱一共有多少邮件
    public int getEmailCount(int userid){
        return emailDao.getEmailCount(userid);
    }

    //加载用户收件箱邮件
    public List<Map<String, Object>> loadEmail(int userid,int pageNow) {
        return emailDao.loadEmail(userid,pageNow);
    }

    //发送邮件
    public void sendEmail(String title, String content, String emaildata, String senderaddress, String acceptoraddress) {
        emailDao.sendEmail(title,content,emaildata,senderaddress,acceptoraddress);
    }
}
