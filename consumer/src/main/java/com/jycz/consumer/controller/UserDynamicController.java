package com.jycz.consumer.controller;

import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.entity.User;
import com.jycz.common.response.Result;
import com.jycz.consumer.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation("用户动态")
@RestController
public class UserDynamicController {

    private final UserService userService;

    public UserDynamicController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{uid}/dynamic")
    public Result getUserDynamicController(@PathVariable("uid") Integer uid) {
        return Result.ofSuccess(userService.getUserDynamic(uid));

    }
}
