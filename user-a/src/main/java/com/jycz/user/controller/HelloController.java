package com.jycz.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @data 2020/4/7 14:01
 */
@Api(tags = "测试controller")
@RestController
public class HelloController {
    @ApiOperation("hello")
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
