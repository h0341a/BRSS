package com.jycz.consumer.controller;

import com.jycz.common.model.entity.UserInfo;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.common.utils.GetUidBySecurity;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.consumer.model.dto.UserInfoDto;
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

    @ApiOperation("添加收藏书籍")
    @PostMapping("/collection")
    public Result addCollection(Integer bid) {
        if (userService.addOrDelCollection(bid, 0)) {
            return Result.ofSuccess("添加成功");
        }
        return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已收藏");
    }

    @ApiOperation("添加点赞记录")
    @PostMapping("/star")
    public Result addStar(Integer rid) {
        if (userService.addOrDelStar(rid, 0)){
            return Result.ofSuccess("点赞成功");

        }
        return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已点赞");
    }

    @ApiOperation("取消喜爱")
    @DeleteMapping("/star")
    public Result cancelStar(Integer rid) {
        if (userService.addOrDelStar(rid, 1)){
            return Result.ofSuccess("取消成功");

        }
        return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "你未点赞");
    }

    @ApiOperation("取消收藏")
    @DeleteMapping("/collection")
    public Result cancelCollection(Integer bid) {
        if (userService.addOrDelCollection(bid, 1)) {
            return Result.ofSuccess("取消成功");
        }
        return Result.ofFail(ErrCodeEnum.USER_OPERATION_PUZZLE, "你未收藏");
    }

    @ApiOperation("判断是否收藏过该书籍")
    @GetMapping("/isCollection")
    public Result isCollection(Integer bid) {
        return Result.ofSuccess(userService.isCollection(bid));
    }

    @ApiOperation("判断是否点赞过该推荐")
    @GetMapping("/isStar")
    public Result isStar(Integer rid) {
        return Result.ofSuccess(userService.isStar(rid));
    }

    @ApiOperation("获取推荐")
    @GetMapping("/recommends")
    public Result getRecommends(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "8") int pageSize) {
        return Result.ofSuccess(userService.getRecommends(page, pageSize));
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result getUserInfo(){
        return Result.ofSuccess(userService.getUserInfo());
    }
    @ApiOperation("修改用户信息")
    @PostMapping("/info")
    public Result alterUserInfo(UserInfoDto userInfoDto){
        if(userService.updateUserInfo(userInfoDto)){
            return Result.ofSuccess("修改成功!");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }
    @ApiOperation("获取头像地址")
    @GetMapping("/avatarUrl")
    public Result getAvatarUrl() {
        Integer uid = GetUidBySecurity.getUid();
        return Result.ofSuccess(userService.getAvatarUrl(uid));
    }

}
