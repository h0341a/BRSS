package com.jycz.common.dao;

import com.jycz.common.model.entity.UserGroup;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupMapper {
    @Select("select id from user_group where uid=#{uid}")
    Integer selectIdByUid(Integer uid);
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}