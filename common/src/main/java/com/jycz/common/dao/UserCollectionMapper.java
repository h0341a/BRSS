package com.jycz.common.dao;

import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserCollection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCollectionMapper {
    @Select("select count(*) from user_collection where uid=#{uid} and bid=#{bid}")
    int selectByBidAndUid(Integer uid, Integer bid);

    @Delete("delete from user_collection where uid=#{uid} and bid=#{bid}")
    int delete(Integer uid, Integer bid);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    List<UserCollection> selectByUid(Integer uid);
}