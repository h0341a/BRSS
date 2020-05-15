package com.jycz.admin.controller;

import com.jycz.admin.service.ReviewService;
import com.jycz.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ling
 * @data 2020/4/12 14:04
 */
@Api(tags = "AdminController:管理员进行的操作")
@RestController
@RequestMapping("/admin/review")
public class AdminController {

    private final ReviewService reviewService;

    public AdminController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation("获取待审核书籍列表")
    @GetMapping("/books")
    public Result getToReviewBookList(@RequestParam(defaultValue = "0") Integer pageNum,
                                      @RequestParam(defaultValue = "12") Integer pageSize){

        return Result.ofSuccess(reviewService.getReviewBookList(pageNum, pageSize));

    }
    @ApiOperation("获取待审核推荐列表")
    @GetMapping("/recommends")
    public Result getToReviewRecommend(@RequestParam(defaultValue = "0") Integer pageNum,
                                     @RequestParam(defaultValue = "12") Integer pageSize){
        return Result.ofSuccess(reviewService.getReviewRecommendList(pageNum, pageSize));

    }


}
