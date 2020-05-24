package com.jycz.common.utils;

import com.jycz.common.model.vo.BookVo;
import com.jycz.common.model.entity.Book;
import org.springframework.beans.BeanUtils;

public class BookModelConverter {
    public static BookVo bookToBookVo(Book book){
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(book, bookVo);
        return bookVo;
    }

}
