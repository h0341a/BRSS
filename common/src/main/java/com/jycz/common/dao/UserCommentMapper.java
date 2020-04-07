package com.jycz.common.dao;

import com.jycz.common.model.entity.UserComment;

public interface UserCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    UserComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKeyWithBLOBs(UserComment record);

    int updateByPrimaryKey(UserComment record);
}