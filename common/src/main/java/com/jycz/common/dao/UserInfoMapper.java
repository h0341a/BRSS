package com.jycz.common.dao;

import com.jycz.common.model.entity.UserInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    @Select("select avatar_url from user_info where uid =#{uid}")
    String selectAvatarUrlByUid(Integer uid);
    UserInfo selectByUid(Integer uid);
    int insert(UserInfo record);

    int insertSelective(UserInfo record);
}