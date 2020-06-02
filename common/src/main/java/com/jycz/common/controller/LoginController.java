package com.jycz.common.controller;

import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.vo.UserVo;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.common.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/8 14:42
 */
@Api(tags = "LoginController:用户登录注册")
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public void login(String username, String password) {

    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@Valid UserDto userDto) throws BusinessException {
        return Result.ofSuccess(loginService.userRegister(userDto));
    }

    @ApiOperation("判断当前用户名是否存在")
    @GetMapping("/usernameIsSave")
    public Result usernameIsSave(String username) {
        int usernameMinSize = 4;
        int usernameMaxSize = 32;
        if (!(username.length() >= usernameMinSize && username.length() <= usernameMaxSize)) {
            return Result.ofFail(ErrCodeEnum.PARAMETERS_INVALID);
        } else {
            if (loginService.usernameIsSave(username)) {
                return Result.ofSuccess("用户名已存在");
            } else {
                return Result.ofFail(ErrCodeEnum.DATA_ABORT, "用户名不存在");
            }

        }
    }

    @GetMapping("/logout")
    public void logout() {

    }
}
