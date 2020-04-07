package jycz.brss.dao;

import jycz.brss.model.entity.RelationGroup;

public interface RelationGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelationGroup record);

    int insertSelective(RelationGroup record);

    RelationGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelationGroup record);

    int updateByPrimaryKey(RelationGroup record);
}