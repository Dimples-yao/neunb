package com.dao;

import com.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Repository
public interface UserDao {

    List<Map<String , Object>> checkLogin(@Param(value="useraddress") String useraddress ,@Param(value = "passwd") String passwd);

    Map<String , Object> getUserMessage(@Param(value = "userid") String userid);

}
