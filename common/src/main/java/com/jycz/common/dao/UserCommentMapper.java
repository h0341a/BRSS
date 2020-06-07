package com.jycz.common.dao;

import com.jycz.common.model.entity.UserComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    UserComment selectByPrimaryKey(Integer id);
    List<UserComment> selectByUid(Integer uid);
    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKeyWithBLOBs(UserComment record);

    int updateByPrimaryKey(UserComment record);
}