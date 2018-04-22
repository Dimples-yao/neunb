package com.service.impl;

import com.model.User;
import com.service.UserService;
import com.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;

    public List<Map<String , Object>> checkLogin(String useraddress, String passwd) {
        return userDao.checkLogin(useraddress , passwd);
    }

    public Map<String, Object> getUserMessage(String userid) {
        return userDao.getUserMessage(userid);
    }
}
