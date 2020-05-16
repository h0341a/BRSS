package com.jycz.book.service;

import com.github.pagehelper.PageInfo;
import com.jycz.book.model.vo.BookVo;
import com.jycz.common.response.BusinessException;

import java.util.List;

public interface BookService {
    public PageInfo<BookVo> getBooks(String status, int page, int pageSize);

}
