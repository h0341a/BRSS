package com.jycz.consumer.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.common.utils.GetUidBySecurity;
import com.jycz.consumer.model.dto.MsgDto;
import com.jycz.consumer.service.UserRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author ling
 * @data 2020/4/8 14:51
 */
@Api(tags = "UserRelationController:用户之间的关系")
@RestController
@RequestMapping("/user")
public class UserRelationController {
    private final UserRelationService userRelationService;

    public UserRelationController(UserRelationService userRelationService) {
        this.userRelationService = userRelationService;
    }

    @ApiOperation("发送消息给某人")
    @PostMapping("/msg")
    public Result sendMsg(MsgDto msgDto) throws BusinessException {
        if (userRelationService.sendMsg(msgDto)) {
            return Result.ofSuccess("发送成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }

    @ApiOperation("获取与某个用户的所有聊天记录")
    @GetMapping("/msgList")
    public Result getMsgList(Integer targetId) throws BusinessException {
        return Result.ofSuccess(userRelationService.getMsgFromSb(targetId));
    }

    @ApiOperation("获取我的好友列表")
    @GetMapping("/friends")
    public Result getMyFriendList() {
        return Result.ofSuccess(userRelationService.getFriendList());
    }

    @ApiOperation("关注某人")
    @PostMapping("/follow")
    public Result follow(Integer targetId) throws BusinessException {
        Integer uid = GetUidBySecurity.getUid();
        if (uid.equals(targetId)) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己哦");
        }

        if (userRelationService.addFollow(uid, targetId)) {
            return Result.ofSuccess("关注成功");
        } else {
            return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR, "关注失败");
        }
    }

    @GetMapping("/relation")
    public Result getUserRelation(@RequestParam("uid") Integer targetId) throws BusinessException {
        return Result.ofSuccess(userRelationService.hasRelation(targetId));
    }

    @ApiOperation("取消关注")
    @DeleteMapping("/follow/{targetId}")
    public Result cancelFollow(@PathVariable Integer targetId) throws BusinessException {
        Integer uid = GetUidBySecurity.getUid();
        if (uid.equals(targetId)) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能取消自己哦");
        }
        if (userRelationService.cancelFollow(uid, targetId)) {
            return Result.ofSuccess("取消成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR, "取消失败");
    }

}
