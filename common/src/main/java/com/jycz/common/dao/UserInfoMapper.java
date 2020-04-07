package com.jycz.common.dao;

import com.jycz.common.model.entity.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}