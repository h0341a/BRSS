package com.jycz.common.dao;

import com.jycz.common.model.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    @Select("select id from role where role_name=#{roleName}")
    Integer selectIdByRoleName(String roleName);
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}