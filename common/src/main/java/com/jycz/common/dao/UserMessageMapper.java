package com.jycz.common.dao;

import com.jycz.common.model.entity.UserMessage;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageMapper {
    List<UserMessage> selectMsgByUid(Integer id1, Integer id2);

    @Update("update user_message set read_status = 1 where send_uid=#{targetId} and accept_uid=#{uid}")
    int updateReadStatus(Integer targetId, Integer uid);

    int selectUnreadMsg(Integer targetId, Integer uid);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);
}