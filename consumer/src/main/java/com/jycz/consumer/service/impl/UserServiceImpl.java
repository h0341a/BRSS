package com.jycz.consumer.service.impl;

import com.jycz.common.dao.*;
import com.jycz.common.model.entity.Book;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserInfo;
import com.jycz.common.model.entity.UserRecommend;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.vo.UserVo;
import com.jycz.consumer.service.UserService;
import com.jycz.consumer.utils.UserModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ling
 * @data 2020/4/8 11:29
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final RoleMapper roleMapper;
    private final BookMapper bookMapper;
    @Autowired
    private UserRecommendMapper recommendMapper;
    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, RoleMapper roleMapper, BookMapper bookMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.roleMapper = roleMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException {
        Book book = UserModelConverter.recommendDtoToBook(uid, recommendDto);
        Integer bid = bookMapper.selectIdByNameAndAuthor(book);
        //如果当前书籍不存在，则插入
        if (bid == null) {
            bookMapper.insertSelective(book);
            //获取新建书籍的主键
            bid = book.getId();
        }
        UserRecommend userRecommend = UserModelConverter.recommendDtoToRecommend(uid, bid, recommendDto);
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
