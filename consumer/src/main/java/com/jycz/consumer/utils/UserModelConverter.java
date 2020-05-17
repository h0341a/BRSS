package com.jycz.consumer.utils;


import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserRecommend;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.vo.UserVo;
import org.springframework.beans.BeanUtils;

/**
 * @author ling
 * @data 2020/4/8 12:43
 */
public class UserModelConverter {
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
