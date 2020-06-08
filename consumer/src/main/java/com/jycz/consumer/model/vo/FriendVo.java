package com.jycz.consumer.model.vo;

import com.jycz.common.model.entity.Book;
import lombok.Data;

@Data
public class FriendVo {
    private Boolean hasMsg;
    private String avatarUrl;
    private String nickname;
    private Integer uid;
}
