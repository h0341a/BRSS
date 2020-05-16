package com.jycz.consumer.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.consumer.model.dto.UserDto;
import com.jycz.consumer.model.vo.UserVo;
import com.jycz.consumer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("判断当前用户名是否存在，且理想的结果应该是返回不存在。")
    @GetMapping("/{username}/save")
    public Result usernameIsSave(@PathVariable String username){
        int usernameMinSize = 4;
        int usernameMaxSize = 32;
        //用户名只能小写字母开头
        String regex = "^[a-z]+[a-zA-Z0-9]+$";
        if (!username.matches(regex)) {
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID);
        }
        System.out.println(username.matches(regex));
        if (username.length() < usernameMinSize || username.length()> usernameMaxSize){
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID);
        }else{
            if(!userService.usernameIsSave(username)){
                return Result.ofSuccess("用户名不存在");
            }else{
                return Result.ofFail(ErrCodeEnum.PARAMETERS_VALIDATION_FAIL);
            }
        }
    }
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@Valid UserDto userDto) throws BusinessException {
        if (StringUtils.isEmpty(userDto.getNickname())){
            userDto.setNickname(userDto.getUsername());
        }
        UserVo userVo = userService.userRegister(userDto);
        return Result.ofSuccess(userVo);
    }

    @PostMapping("/recommend")
    public Result addBookRecommend(@Valid RecommendDto recommendDto) throws BusinessException {
        Integer uid = 3;
        if(userService.addBookRecommend(uid, recommendDto)){
            return Result.ofSuccess("添加推荐成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }
}
