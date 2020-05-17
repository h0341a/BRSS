package com.jycz.common.service;

import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.vo.UserVo;
import com.jycz.common.response.BusinessException;

public interface LoginService {
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
