package com.jycz.book.controller;

import com.jycz.book.model.dto.RecommendDto;
import com.jycz.book.service.BookService;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/12 13:04
 */
@Api(tags = "BookController:图书会被普通用户进行的操作")
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
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
