package com.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/7/19.
 */
@Repository
public interface EmailDao {
    int getEmailCount (@Param(value = "userid") int userid);
    List<Map<String , Object>> loadEmail(@Param(value = "userid") int userid,@Param(value = "pageNow") int pageNow);
    void sendEmail(String title,String content,String emaildata,String senderaddress,String acceptoraddress);
}
