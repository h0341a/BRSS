package com.jycz.common.dao;

import com.jycz.common.model.entity.UserRecommend;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRecommendMapper {
    @Select("select id from user_recommend where uid=#{uid} and bid=#{bid}")
    Integer selectIdByUidAndBid(Integer uid, Integer bid);
    @Select("select * from user_recommend")
    List<UserRecommend> selectAll();

    List<UserRecommend> selectByBid(Integer bid);
    List<UserRecommend> selectByUid(Integer uid);

    List<UserRecommend> selectToReviewList();

    int deleteByPrimaryKey(Integer id);

    int insert(UserRecommend record);

    int insertSelective(UserRecommend record);

    UserRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRecommend record);

    int updateByPrimaryKeyWithBLOBs(UserRecommend record);

    int updateByPrimaryKey(UserRecommend record);
}