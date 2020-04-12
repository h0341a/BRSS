package com.jycz.user.utils;

import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserRecommend;
import com.jycz.user.model.dto.RecommendDto;
import com.jycz.user.model.dto.UserDto;
import com.jycz.user.model.vo.UserVo;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;

/**
 * @author ling
 * @data 2020/4/8 12:43
 */
public class MyModelConverter {
    public static User userDtoToUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
    public static UserVo userToUserVo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
    public static Book recommendDtoToBook(Integer uid,RecommendDto recommendDto){
        Book book = new Book();
        book.setName(recommendDto.getBookName());
        book.setAuthor(recommendDto.getBookAuthor());
        book.setIntroduction(recommendDto.getIntroduction());
        book.setCommitUser(uid);
        return book;
    }
    public static UserRecommend recommendDtoToRecommend(Integer uid,Integer bid, RecommendDto recommendDto){
        UserRecommend userRecommend = new UserRecommend();
        userRecommend.setContent(recommendDto.getContent());
        userRecommend.setUid(uid);
        userRecommend.setBid(bid);
        return userRecommend;
    }
}
