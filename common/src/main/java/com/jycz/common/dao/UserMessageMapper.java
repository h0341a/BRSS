package com.jycz.common.dao;

import com.jycz.common.model.entity.UserMessage;

public interface UserMessageMapper {
    int insert(UserMessage record);

    int insertSelective(UserMessage record);
}