package jycz.brss.dao;

import jycz.brss.model.entity.UserMessage;

public interface UserMessageMapper {
    int insert(UserMessage record);

    int insertSelective(UserMessage record);
}