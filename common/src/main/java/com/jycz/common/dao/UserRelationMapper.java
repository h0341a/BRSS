package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRelation;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationMapper {
    @Update("update user_relation set status = 2 where uid=#{uid} and idol_id=#{idolId}")
    int updateToEachOther(Integer uid, Integer idolId);
    Integer selectStatusByUidAndIdolId(Integer uid, Integer idolId);
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}