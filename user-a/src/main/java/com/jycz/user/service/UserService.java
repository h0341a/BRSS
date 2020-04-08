package com.jycz.user.service;

/**
 * @author ling
 * @data 2020/4/8 11:25
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return save or no save
     */
    public boolean usernameIsSave(String username);
}
