package com.yanlei.service.impl;

import com.yanlei.dao.UserDao;
import com.yanlei.model.User;
import com.yanlei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: x
 * @Date: Created in 15:11 2018/3/8
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public String findUsername(String username) {
        return userDao.findUsername(username);

    }

    public int addUser(String username, String pass) {
        return userDao.addUser(username,pass);
    }

    public User findUserAndPass(String username, String pass) {
        return userDao.findUserAndPass(username,pass);
    }
}
