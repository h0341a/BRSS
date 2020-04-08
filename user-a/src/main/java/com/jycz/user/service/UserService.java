package com.jycz.user.service;

import com.jycz.common.response.BusinessException;
import com.jycz.user.model.dto.UserDto;
import com.jycz.user.model.vo.UserVo;

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
    /**
     * 注册用户
     * @param userDto 用户注册信息
     * @return 注册是否成功
     * @throws BusinessException 当用户名已存在抛出该异常返回相应提示
     */
    public UserVo userRegister(UserDto userDto) throws BusinessException;
}
