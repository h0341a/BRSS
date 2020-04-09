package com.jycz.common.dao;

import com.jycz.common.model.entity.RelationGroup;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationGroupMapper {
    @Select("select id from relation_group where group_name=#{name}")
    Integer selectIdByName(String name);
    int deleteByPrimaryKey(Integer id);

    int insert(RelationGroup record);

    int insertSelective(RelationGroup record);

    RelationGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationGroup record);

    int updateByPrimaryKey(RelationGroup record);
}