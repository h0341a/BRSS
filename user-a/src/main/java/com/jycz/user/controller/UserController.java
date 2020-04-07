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
    @GetMapping("/hello")
    public Result hello(){
        return Result.ofSuccess("123");
    }
    @GetMapping("/hello1")
    public Result hello1(){
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }
    @GetMapping("/hello2")
    public Result hello2(){
        int i =2/0;
        return Result.ofSuccess("123");
    }
    @GetMapping("/hello3")
    public Result hello3() throws BusinessException {
        throw new BusinessException(ErrCodeEnum.UNKNOWN_ERROR);
    }
}
