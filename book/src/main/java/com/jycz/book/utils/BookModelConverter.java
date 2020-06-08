package com.jycz.book.utils;

import com.jycz.book.model.vo.BookDetailsVo;
import com.jycz.book.model.vo.BookVo;
import com.jycz.book.model.vo.RecommendItemVo;
import com.jycz.book.model.vo.RecommendVo;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserInfo;
import com.jycz.common.model.entity.UserRecommend;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BookModelConverter {
    public static BookVo bookToBookVo(Book book){
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(book, bookVo);
        return bookVo;
    }

    public static RecommendVo recommendAndBookToRecommendVo(UserRecommend recommend, Book book, User user){
        RecommendVo recommendVo = new RecommendVo();
        recommendVo.setBid(book.getId());
        recommendVo.setCoverUrl(book.getCoverUrl());
        recommendVo.setBookName(book.getName());
        recommendVo.setUid(recommend.getUid());
        recommendVo.setNickname(user.getNickname());
        recommendVo.setContent(recommend.getContent());
        recommendVo.setRid(recommend.getId());
        return recommendVo;
    }

    public static BookDetailsVo bookToBookDetails(Book book, List<RecommendItemVo> userRecommendList){
        BookDetailsVo bookDetailsVo = new BookDetailsVo();
        BeanUtils.copyProperties(book, bookDetailsVo);
        bookDetailsVo.setRecommendVoList(userRecommendList);
        return bookDetailsVo;
    }
    public static RecommendItemVo recommendToItem(UserRecommend recommend, String avatarUrl){
        RecommendItemVo item = new RecommendItemVo();
        item.setTitle(recommend.getTitle());
        item.setContent(recommend.getContent());
        item.setRid(recommend.getId());
        item.setAvatarUrl(avatarUrl);
        return item;
    }
}
