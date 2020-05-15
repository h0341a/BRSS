package com.jycz.common.controller;

import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @data 2020/4/8 14:42
 */
@Api(tags = "LoginController:用户登录")
@RestController
public class LoginController {
    @PostMapping("/login")
    public void login(){

    }
    @GetMapping("/logout")
    public void logout(){

    }
}
