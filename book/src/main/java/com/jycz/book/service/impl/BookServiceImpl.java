package com.jycz.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jycz.book.model.vo.BookVo;
import com.jycz.book.service.BookService;
import com.jycz.book.utils.BookModelConverter;
import com.jycz.common.dao.BookMapper;
import com.jycz.common.dao.UserCollectionMapper;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserCollection;
import com.jycz.common.utils.GetUidBySecurity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final UserCollectionMapper collectionMapper;
    public BookServiceImpl(BookMapper bookMapper, UserCollectionMapper collectionMapper) {
        this.bookMapper = bookMapper;
        this.collectionMapper = collectionMapper;
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

    @Override
    public PageInfo<BookVo> getCollectBooks(int page, int pageSize) {
        Integer uid = GetUidBySecurity.getUid();
        PageHelper.startPage(page, pageSize);
        List<UserCollection> collections = collectionMapper.selectByUid(uid);
        List<BookVo> bookVoList = new ArrayList<>();
        collections.forEach(collection -> {
            Book book = bookMapper.selectByPrimaryKey(collection.getBid());
            BookVo bookVo = BookModelConverter.bookToBookVo(book);
            bookVo.setCollectionDate(collection.getCollectionDate());
            bookVoList.add(bookVo);
        });
        return new PageInfo<BookVo>(bookVoList);
    }
}
