package com.yanlei.dao;

import com.yanlei.model.User;

/**
 * @Author: x
 * @Date: Created in 15:24 2018/3/8
 */
public interface UserDao {
    String findUsername(String username);

    int addUser(String username, String pass);

    User findUserAndPass(String username, String pass);
}
