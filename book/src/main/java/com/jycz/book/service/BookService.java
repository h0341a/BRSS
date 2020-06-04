package com.jycz.book.service;

import com.github.pagehelper.PageInfo;
import com.jycz.book.model.vo.BookVo;
import com.jycz.book.model.vo.RecommendVo;

public interface BookService {
    public PageInfo<RecommendVo> getRecommends(int page, int pageSize);
    public PageInfo<BookVo> getBooks(String status, int page, int pageSize);
    /**
     * 获取用户收藏的书籍
     * @param page 页码
     * @param pageSize 页容量
     * @return 书籍列表
     */
    public PageInfo<BookVo> getCollectBooks(int page, int pageSize);
}
