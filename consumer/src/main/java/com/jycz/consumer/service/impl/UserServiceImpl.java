package com.jycz.consumer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jycz.common.dao.*;
import com.jycz.common.model.entity.*;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.utils.GetUidBySecurity;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.consumer.model.dto.UserInfoDto;
import com.jycz.consumer.model.vo.RecommendVo;
import com.jycz.consumer.model.vo.UserInfoVo;
import com.jycz.consumer.service.UserService;
import com.jycz.consumer.utils.UserModelConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private final UserRecommendMapper recommendMapper;
    private final UserCollectionMapper collectionMapper;

    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, RoleMapper roleMapper, BookMapper bookMapper, UserRecommendMapper recommendMapper, UserCollectionMapper collectionMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.roleMapper = roleMapper;
        this.bookMapper = bookMapper;
        this.recommendMapper = recommendMapper;
        this.collectionMapper = collectionMapper;
    }

    @Override
    public UserInfoVo getUserInfo() {
        UserInfo userInfo = userInfoMapper.selectByUid(GetUidBySecurity.getUid());
        User user = userMapper.selectByPrimaryKey(GetUidBySecurity.getUid());
        return UserModelConverter.userInfoToUserInfoVo(userInfo, user);
    }

    @Override
    public boolean updateUserInfo(UserInfoDto userInfoDto) {
        int n = 0,m = 0;
        if (!StringUtils.isEmpty(userInfoDto.getNickname())) {
            User user = new User();
            user.setId(GetUidBySecurity.getUid());
            user.setNickname(userInfoDto.getNickname());
            n = userMapper.updateByPrimaryKeySelective(user);
        }
        UserInfo userInfo = new UserInfo();
        if (StringUtils.isEmpty(userInfo.getBio())){
            userInfo.setBio(null);
        }
        if (StringUtils.isEmpty(userInfo.getAvatarUrl())){
            userInfo.setAvatarUrl(null);
        }
        userInfo.setUid(GetUidBySecurity.getUid());
        userInfo.setAvatarUrl(userInfoDto.getAvatar());
        userInfo.setBio(userInfoDto.getBio());
        m =userInfoMapper.updateByUidSelective(userInfo);
        return n == 1 || m == 1;
    }

    @Override
    public boolean addOrDelCollection(Integer bid, int option) {
        int n = collectionMapper.selectByBidAndUid(GetUidBySecurity.getUid(), bid);
        if (option == 0 && n == 0) {
            UserCollection userCollection = new UserCollection();
            userCollection.setBid(bid);
            userCollection.setUid(GetUidBySecurity.getUid());
            return collectionMapper.insertSelective(userCollection) != 0;
        } else if (option == 1 && n == 1) {
            return collectionMapper.delete(GetUidBySecurity.getUid(), bid) != 0;
        } else {
            return false;
        }

    }

    @Override
    public boolean addOrDelStar(Integer rid, int option) {
        int n = userMapper.selectStarByUidAndRid(GetUidBySecurity.getUid(), rid);
        if (option == 0 && n == 0) {
            userMapper.insertStar(GetUidBySecurity.getUid(), rid);
            return true;
        } else if (option == 1 && n == 1) {
            userMapper.deleteStar(GetUidBySecurity.getUid(), rid);
            return true;
        }
        return false;
    }

    @Override
    public boolean isCollection(Integer bid) {
        Integer uid = GetUidBySecurity.getUid();
        return collectionMapper.selectByBidAndUid(uid, bid) != 0;
    }

    @Override
    public boolean isStar(Integer rid) {
        Integer uid = GetUidBySecurity.getUid();
        return userMapper.selectStarByUidAndRid(uid, rid) != 0;
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

    @Override
    public PageInfo<RecommendVo> getRecommends(int page, int pageSize) {
        Integer uid = GetUidBySecurity.getUid();
        PageHelper.startPage(page, pageSize);
        List<UserRecommend> recommendList = recommendMapper.selectByUid(uid);
        List<RecommendVo> recommendVoList = new ArrayList<>();
        recommendList.forEach(recommend -> {
            Book book = bookMapper.selectByPrimaryKey(recommend.getBid());
            recommendVoList.add(UserModelConverter.recommendAndBookToRecommendVo(recommend, book));
        });
        return new PageInfo<RecommendVo>(recommendVoList);
    }

    @Override
    public String getAvatarUrl(Integer uid) {
        return userInfoMapper.selectAvatarUrlByUid(uid);
    }

}
