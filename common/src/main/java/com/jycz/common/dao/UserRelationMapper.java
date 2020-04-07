package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRelation;

public interface UserRelationMapper {
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}