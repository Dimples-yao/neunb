package com.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {

    //登录功能
    List<Map<String , Object>> checkLogin(@Param("useraddress") String useraddress, @Param("passwd") String passwd);

    //查看该用户的信息
    Map<String , Object> getUserMessage(@Param("userid") String userid);

}
