package com.jycz.user.controller;

import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import com.jycz.user.model.dto.RecommendDto;
import com.jycz.user.service.BookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/12 13:04
 */
@Api(tags = "UserToBookController:普通用户对图书进行的操作")
@RestController
@RequestMapping("/book")
public class UserToBookController {
    private final BookService bookService;

    public UserToBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/recommend")
    public Result addBookRecommend(@Valid RecommendDto recommendDto) throws BusinessException {
        Integer uid = 3;
        if(bookService.addBookRecommend(uid, recommendDto)){
            return Result.ofSuccess("添加推荐成功");
        }
        return Result.ofFail(ErrCodeEnum.UNKNOWN_ERROR);
    }
}
