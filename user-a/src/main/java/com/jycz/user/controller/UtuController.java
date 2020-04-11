package com.jycz.user.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.user.service.UserRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ling
 * @data 2020/4/8 14:51
 */
@Api(tags = "UserToUserController:用户之间的关系")
@RestController
public class UtuController {
    private final UserRelationService userRelationService;

    public UtuController(UserRelationService userRelationService) {
        this.userRelationService = userRelationService;
    }

    @ApiOperation("关注某人且放入分组")
    @PostMapping("/follow/{targetId}")
    public Result follow(@PathVariable Integer targetId, String groupName) throws BusinessException {
        //uid应该从session里拿
        Integer uid = 2;
        if (uid.equals(targetId)){
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己哦");
        }
        if (StringUtils.isEmpty(groupName)){
            groupName = "未分组";
        }
        if(userRelationService.addFollow(uid, targetId, groupName)){
            return Result.ofSuccess("关注成功");
        }else{
            return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR, "关注失败");
        }
    }

    @ApiOperation("拉黑某人")
    @PostMapping("/blacklist/{targetId}")
    public Result blacklist(@PathVariable Integer targetId) throws BusinessException {
        Integer uid = 2;
        if (uid.equals(targetId)){
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己哦");
        }
        if(userRelationService.joinBlacklist(uid,targetId)){
            return Result.ofSuccess("已拉黑");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }

    @ApiOperation("取消关注")
    @DeleteMapping("/follow/{targetId}")
    public Result cancelFollow(@PathVariable Integer targetId) throws BusinessException {
        Integer uid = 2;
        if (uid.equals(targetId)){
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己哦");
        }
        if (userRelationService.cancelFollow(uid ,targetId)){
            return Result.ofSuccess("取消成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR, "取消失败");
    }
    @ApiOperation("取消拉黑某人")
    @DeleteMapping("/blacklist/{targetId}")
    public Result deleteFromBlacklist(@PathVariable Integer targetId) throws BusinessException {
        Integer uid = 2;
        if (uid.equals(targetId)){
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己哦");
        }
        if (userRelationService.deleteFromBlacklist(uid ,targetId)){
            return Result.ofSuccess("取消成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR, "取消失败");
    }

}
