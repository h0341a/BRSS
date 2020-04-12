package com.jycz.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jycz.admin.service.ReviewService;
import com.jycz.common.dao.BookMapper;
import com.jycz.common.dao.UserRecommendMapper;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ling
 * @data 2020/4/12 14:32
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    private final BookMapper bookMapper;
    private final UserRecommendMapper recommendMapper;

    public ReviewServiceImpl(BookMapper bookMapper, UserRecommendMapper recommendMapper) {
        this.bookMapper = bookMapper;
        this.recommendMapper = recommendMapper;
    }

    @Override
    public PageInfo<Book> getReviewBookList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //获取所有待审核的书籍
        List<Book> bookList = bookMapper.selectAllByStatus(0);
        return new PageInfo<Book>(bookList);
    }

    @Override
    public PageInfo<UserRecommend> getReviewRecommendList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserRecommend> recommendList = recommendMapper.selectToReviewList();
        return new PageInfo<UserRecommend>(recommendList);
    }
}
