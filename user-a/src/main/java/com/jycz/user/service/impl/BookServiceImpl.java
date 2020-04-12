package com.jycz.user.service.impl;

import com.jycz.common.dao.BookMapper;
import com.jycz.common.dao.UserRecommendMapper;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.UserRecommend;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.user.model.dto.RecommendDto;
import com.jycz.user.service.BookService;
import com.jycz.user.utils.MyModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.BufferOverflowException;

/**
 * @author ling
 * @data 2020/4/12 13:30
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final UserRecommendMapper recommendMapper;

    public BookServiceImpl(BookMapper bookMapper, UserRecommendMapper recommendMapper) {
        this.bookMapper = bookMapper;
        this.recommendMapper = recommendMapper;
    }

    @Override
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException {
        Book book = MyModelConverter.recommendDtoToBook(uid, recommendDto);
        Integer bid = bookMapper.selectIdByNameAndAuthor(book);
        //如果当前书籍不存在，则插入
        if (bid == null) {
            bookMapper.insertSelective(book);
            //获取新建书籍的主键
            bid = book.getId();
        }
        UserRecommend userRecommend = MyModelConverter.recommendDtoToRecommend(uid, bid, recommendDto);
        //根据用户id与书籍id判断该推荐是否唯一
        Integer rid = recommendMapper.selectIdByUidAndBid(uid, bid);
        if (rid != null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你只能对同一书籍进行一次推荐");
        }
        if (recommendMapper.insertSelective(userRecommend) == 1) {
            return true;
        }
        return false;
    }
}