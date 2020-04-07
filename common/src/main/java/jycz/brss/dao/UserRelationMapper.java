package jycz.brss.dao;

import jycz.brss.model.entity.UserRelation;

public interface UserRelationMapper {
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}