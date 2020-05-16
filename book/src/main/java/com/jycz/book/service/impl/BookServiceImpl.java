package com.jycz.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jycz.book.model.vo.BookVo;
import com.jycz.book.service.BookService;
import com.jycz.book.utils.BookModelConverter;
import com.jycz.common.dao.BookMapper;
import com.jycz.common.model.entity.Book;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public PageInfo<BookVo> getBooks(String status, int page, int pageSize) {
        //status 判断首页展示数据的方式(time:按时间排序， rank：按评分排序， random：随机推荐)
        if ( !StringUtils.equals("time", status)
                && !StringUtils.equals("rank", status)
                && !StringUtils.equals("random", status)) {
            status = "time";
        }
        PageHelper.startPage(page, pageSize);
        List<Book> books = bookMapper.selectBooksForHome(status);

        List<BookVo> bookVoList = new ArrayList<>();
        books.forEach(book -> {
            bookVoList.add(BookModelConverter.bookToBookVo(book));
        });
        return new PageInfo<BookVo>(bookVoList);
    }
}
