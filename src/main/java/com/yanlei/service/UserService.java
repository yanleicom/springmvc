package com.yanlei.service;

import com.yanlei.model.User;

/**
 * @Author: x
 * @Date: Created in 15:11 2018/3/8
 */

public interface UserService {
    String findUsername(String username);

    int addUser(String username, String pass);

    User findUserAndPass(String username, String pass);
}
