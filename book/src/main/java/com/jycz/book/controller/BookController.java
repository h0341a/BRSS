package com.jycz.book.controller;

import com.jycz.book.service.BookService;
import com.jycz.common.config.security.LoginUser;
import com.jycz.common.config.security.MyUserDetailsService;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/12 13:04
 */
@Api(tags = "BookController:对图书进行的相关操作")
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation("获取首页展示书籍")
    @GetMapping("/books")
    public Result getHomeBooks(
            @RequestParam(defaultValue = "time") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int pageSize) throws BusinessException {
        return Result.ofSuccess(bookService.getBooks(status, page, pageSize));
    }

    @ApiOperation("获取首页展示推荐")
    @GetMapping("/recommends")
    public Result getHomeRecommends(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int pageSize) throws BusinessException {
        return Result.ofSuccess(bookService.getRecommends(page, pageSize));
    }

    @ApiOperation("获取用户收藏书籍")
    @GetMapping("/user/collect")
    public Result getCollectBooks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "8") int pageSize) {
        return Result.ofSuccess(bookService.getCollectBooks(page, pageSize));

    }

    @ApiOperation("获取某个书籍的信息")
    @GetMapping("/book/{bid}")
    public Result getBookInfo(@PathVariable("bid") Integer bid) throws BusinessException {
        return Result.ofSuccess(bookService.getBookDetails(bid));
    }

    @ApiOperation("获取某个推荐的信息")
    @GetMapping("/recommend/{rid}")
    public Result getRecommendInfo(@PathVariable("rid") String rid) {
        return null;
    }
}
