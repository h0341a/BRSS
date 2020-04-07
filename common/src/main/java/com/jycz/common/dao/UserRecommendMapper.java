package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRecommend;

public interface UserRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRecommend record);

    int insertSelective(UserRecommend record);

    UserRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRecommend record);

    int updateByPrimaryKeyWithBLOBs(UserRecommend record);

    int updateByPrimaryKey(UserRecommend record);
}