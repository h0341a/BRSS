package com.jycz.book.service;

import com.github.pagehelper.PageInfo;
import com.jycz.common.model.vo.BookVo;

public interface BookService {
    public PageInfo<BookVo> getBooks(String status, int page, int pageSize);

}
