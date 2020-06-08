package com.jycz.common.dao;

import com.jycz.common.model.entity.UserMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageMapper {
    List<UserMessage> selectMsgByUid(Integer id1, Integer id2);
    int insert(UserMessage record);

    int insertSelective(UserMessage record);
}