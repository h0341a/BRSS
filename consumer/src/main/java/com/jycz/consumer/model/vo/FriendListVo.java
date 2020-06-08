package com.jycz.consumer.model.vo;

import com.jycz.common.model.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
public class FriendListVo {
    public List<FriendVo> followUser;
    public List<FriendVo> userFollow;

    public FriendListVo(List<FriendVo> followUser, List<FriendVo> userFollow) {
        this.followUser = followUser;
        this.userFollow = userFollow;
    }
}
