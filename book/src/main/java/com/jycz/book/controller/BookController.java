package com.jycz.book.controller;

import com.jycz.book.service.BookService;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ling
 * @data 2020/4/12 13:04
 */
@Api(tags = "BookController:对图书进行的相关操作")
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    public Result getShowBooks(
            @RequestParam(defaultValue = "time") String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int pageSize) throws BusinessException {
        return Result.ofSuccess(bookService.getBooks(status, page, pageSize));
    }
}
