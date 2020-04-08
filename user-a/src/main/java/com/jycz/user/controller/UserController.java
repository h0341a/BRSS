package com.jycz.user.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @data 2020/4/7 17:00
 */
@Api(tags = "负责用户账号的操作")
@RestController
public class UserController {
    public Result register(){
        return null;
    }
}
