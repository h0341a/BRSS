package com.jycz.consumer.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.common.utils.GetUidBySecurity;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.consumer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/7 17:00
 */
@Api(tags = "UserController:普通用户操作")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("添加推荐")
    @PostMapping("/recommend")
    public Result addBookRecommend(@Valid RecommendDto recommendDto) throws BusinessException {
        Integer uid = GetUidBySecurity.getUid();
        if (userService.addBookRecommend(uid, recommendDto)) {
            return Result.ofSuccess("添加推荐成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }

    @ApiOperation("获取推荐")
    @GetMapping("/recommends")
    public Result getRecommends(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int pageSize) {
        return Result.ofSuccess(userService.getRecommends(page, pageSize));
    }

    @ApiOperation("获取头像地址")
    @GetMapping("/avatarUrl")
    public Result getAvatarUrl() {
        Integer uid = GetUidBySecurity.getUid();
        return Result.ofSuccess(userService.getAvatarUrl(uid));
    }

}
