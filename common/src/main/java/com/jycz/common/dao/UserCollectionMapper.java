package com.jycz.common.dao;

import com.jycz.common.model.entity.UserCollection;

public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);
}