package jycz.brss.dao;

import jycz.brss.model.entity.UserCollection;

public interface UserCollectionMapper {
    int insert(UserCollection record);

    int insertSelective(UserCollection record);
}