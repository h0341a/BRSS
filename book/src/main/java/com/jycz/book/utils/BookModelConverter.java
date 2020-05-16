package com.jycz.book.utils;

import com.jycz.book.model.vo.BookVo;
import com.jycz.common.model.entity.Book;
import org.springframework.beans.BeanUtils;

public class BookModelConverter {
    public static BookVo bookToBookVo(Book book){
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(book, bookVo);
        return bookVo;
    }

}
