package com.jycz.book.utils;

import com.jycz.book.model.dto.RecommendDto;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserRecommend;

public class BookModelConverter {
    public static Book recommendDtoToBook(Integer uid, RecommendDto recommendDto){
        Book book = new Book();
        book.setName(recommendDto.getBookName());
        book.setAuthor(recommendDto.getBookAuthor());
        book.setIntroduction(recommendDto.getIntroduction());
        book.setCommitUser(uid);
        return book;
    }
    public static UserRecommend recommendDtoToRecommend(Integer uid, Integer bid, RecommendDto recommendDto){
        UserRecommend userRecommend = new UserRecommend();
        userRecommend.setContent(recommendDto.getContent());
        userRecommend.setUid(uid);
        userRecommend.setBid(bid);
        return userRecommend;
    }
}
