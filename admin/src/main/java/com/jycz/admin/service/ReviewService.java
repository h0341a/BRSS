package com.jycz.admin.service;

import com.github.pagehelper.PageInfo;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserRecommend;
import io.swagger.models.auth.In;

/**
 * @author ling
 * @data 2020/4/12 14:28
 */
public interface ReviewService {
    /**
     * 获取待审核的书籍列表
     * @param pageNum 页数
     * @param pageSize 页面大小
     * @return 书籍列表
     */
    public PageInfo<Book> getReviewBookList(Integer pageNum, Integer pageSize);
    /**
     * 获取待审核的推荐列表
     * @param pageNum 页数
     * @param pageSize 页面大小
     * @return 书籍列表
     */
    public PageInfo<UserRecommend> getReviewRecommendList(Integer pageNum, Integer pageSize);

}
