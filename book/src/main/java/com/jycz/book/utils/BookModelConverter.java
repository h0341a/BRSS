package com.jycz.book.utils;

import com.jycz.book.model.vo.BookVo;
import com.jycz.book.model.vo.RecommendVo;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserRecommend;
import org.springframework.beans.BeanUtils;

public class BookModelConverter {
    public static BookVo bookToBookVo(Book book){
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(book, bookVo);
        return bookVo;
    }

    public static RecommendVo recommendAndBookToRecommendVo(UserRecommend recommend, Book book, User user){
        RecommendVo recommendVo = new RecommendVo();
        recommendVo.setBid(book.getId());
        recommendVo.setBookName(book.getName());
        recommendVo.setUid(recommend.getUid());
        recommendVo.setNickname(user.getNickname());
        recommendVo.setContent(recommend.getContent());
        recommendVo.setRid(recommend.getId());
        return recommendVo;
    }

}
