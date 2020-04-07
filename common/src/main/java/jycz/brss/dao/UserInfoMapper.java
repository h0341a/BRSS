package jycz.brss.dao;

import jycz.brss.model.entity.UserInfo;

public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}