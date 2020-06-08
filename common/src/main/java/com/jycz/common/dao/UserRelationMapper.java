package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationMapper {
    @Select("select target_id from user_relation where source_id=#{sourceId}")
    List<Integer> selectTargetIdBySourceId(Integer sourceId);
    @Select("select source_id from user_relation where target_id=#{targetId}")
    List<Integer> selectSourceIdByTargetId(Integer targetId);
    @Delete("delete from user_relation where source_id=#{sourceId} and target_id=#{targetId}")
    int delete(Integer sourceId, Integer targetId);
    @Update("update user_relation set status = #{status} where source_id=#{sourceId} and target_id=#{targetId}")
    int updateRelationStatus(Integer sourceId, Integer targetId, Boolean status);
    Boolean selectStatusByBothId(Integer sourceId, Integer targetId);
    int insert(UserRelation record);

    int insertSelective(UserRelation record);
}