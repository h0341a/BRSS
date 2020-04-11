package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRelationMapper {
    @Delete("delete from user_relation where source_id=#{sourceId} and target_id=#{targetId}")
    int delete(Integer sourceId, Integer targetId);
    @Update("update user_relation set status = #{status} where source_id=#{sourceId} and target_id=#{targetId}")
    int updateRelationStatus(Integer sourceId, Integer targetId, Boolean status);
    Boolean selectStatusByBothId(Integer sourceId, Integer targetId);
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}