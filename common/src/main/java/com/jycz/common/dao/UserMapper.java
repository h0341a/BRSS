package com.jycz.common.dao;

import com.jycz.common.model.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 返回值
     */
    User selectByUsername(String username);
    @Select("select count(*) from star where uid=#{uid} and rid=#{rid}")
    int selectStarByUidAndRid(Integer uid, Integer rid);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}