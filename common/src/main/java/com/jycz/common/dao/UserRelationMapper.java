package com.jycz.common.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationMapper {
    @Delete("delete from user_relation where uid=#{uid} and idol_id=#{idolId}")
    int delete(Integer uid, Integer idolId);
    @Update("update user_relation set status = #{status} where uid=#{uid} and idol_id=#{idolId}")
    int updateToEachOther(Integer uid, Integer idolId, Integer status);
    Integer selectStatusByUidAndIdolId(Integer uid, Integer idolId);
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}