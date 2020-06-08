package com.jycz.consumer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jycz.common.dao.*;
import com.jycz.common.model.entity.*;
import com.jycz.consumer.model.dto.AddRecommendDto;
import com.jycz.consumer.model.vo.DynamicVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final UserCommentMapper commentMapper;

    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, RoleMapper roleMapper, BookMapper bookMapper, UserRecommendMapper recommendMapper, UserCollectionMapper collectionMapper, UserCommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.roleMapper = roleMapper;
        this.bookMapper = bookMapper;
        this.recommendMapper = recommendMapper;
        this.collectionMapper = collectionMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<DynamicVo> getUserDynamic(Integer uid) {
        List<UserRecommend> recommendList = recommendMapper.selectByUid(uid);
        List<Star> starList = userMapper.selectStarByUid(uid);
        List<UserCollection> collectionList = collectionMapper.selectByUid(uid);
        List<UserComment> userComments = commentMapper.selectByUid(uid);
        return UserModelConverter.mergeMultiList(recommendList, starList, collectionList, userComments);
    }

    @Override
    public UserInfoVo getUserInfo() {
        UserInfo userInfo = userInfoMapper.selectByUid(GetUidBySecurity.getUid());
        User user = userMapper.selectByPrimaryKey(GetUidBySecurity.getUid());
        return UserModelConverter.userInfoToUserInfoVo(userInfo, user);
    }

    @Override
    public UserInfoVo getUserInfo(Integer uid) {
        UserInfo userInfo = userInfoMapper.selectByUid(uid);
        User user = userMapper.selectByPrimaryKey(uid);
        return UserModelConverter.userInfoToUserInfoVo(userInfo, user);
    }

    @Override
    public boolean updateUserInfo(UserInfoDto userInfoDto) {
        int n = 0, m = 0;
        if (!StringUtils.isEmpty(userInfoDto.getNickname())) {
            User user = new User();
            user.setId(GetUidBySecurity.getUid());
            user.setNickname(userInfoDto.getNickname());
            n = userMapper.updateByPrimaryKeySelective(user);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(GetUidBySecurity.getUid());
        userInfo.setBio(!StringUtils.isEmpty(userInfoDto.getBio()) ? userInfoDto.getBio() : null);
        userInfo.setAvatarUrl(!StringUtils.isEmpty(userInfoDto.getAvatar()) ? userInfoDto.getAvatar() : null);
        System.out.println(userInfoDto);
        m = userInfoMapper.updateByUidSelective(userInfo);
        return n == 1 || m == 1;
    }

    @Override
    public boolean addOrDelCollection(Integer bid, int option) {
        Integer uid = GetUidBySecurity.getUid();
        int n = collectionMapper.selectByBidAndUid(uid, bid);
        if (option == 0 && n == 0) {
            //添加到收藏数据库
            UserCollection userCollection = new UserCollection();
            userCollection.setBid(bid);
            userCollection.setUid(uid);
            //更新用户数据库的收藏数
            UserInfo userInfo = userInfoMapper.selectByUid(uid);
            userInfo.setCollections(userInfo.getCollections() + 1);
            userInfoMapper.updateByUidSelective(userInfo);
            return collectionMapper.insertSelective(userCollection) != 0;
        } else if (option == 1 && n == 1) {
            //更新用户数据库的收藏数
            UserInfo userInfo = userInfoMapper.selectByUid(uid);
            if (userInfo.getCollections() >= 1) {
                userInfo.setCollections(userInfo.getCollections() - 1);
            }
            userInfoMapper.updateByUidSelective(userInfo);
            return collectionMapper.delete(uid, bid) != 0;
        } else {
            return false;
        }

    }

    @Override
    public boolean addOrDelStar(Integer rid, int option) {
        Integer uid = GetUidBySecurity.getUid();
        int n = userMapper.selectStarByUidAndRid(uid, rid);
        if (option == 0 && n == 0) {
            userMapper.insertStar(uid, rid);
            UserRecommend userRecommend = recommendMapper.selectByPrimaryKey(rid);
            userRecommend.setStars(userRecommend.getStars() + 1);
            recommendMapper.updateByPrimaryKey(userRecommend);
            return true;
        } else if (option == 1 && n == 1) {
            UserRecommend userRecommend = recommendMapper.selectByPrimaryKey(rid);
            if (userRecommend.getStars() > 1) {
                userRecommend.setStars(userRecommend.getStars() - 1);
            }
            recommendMapper.updateByPrimaryKey(userRecommend);
            userMapper.deleteStar(uid, rid);
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
            //更新用户数据库的推荐数
            UserInfo userInfo = userInfoMapper.selectByUid(uid);
            userInfo.setRecommends(userInfo.getRecommends() + 1);
            userInfoMapper.updateByUidSelective(userInfo);
            return true;
        }
        return false;
    }

    @Override
    public boolean addBookRecommend(AddRecommendDto addRecommendDto) throws BusinessException {
        Integer n = recommendMapper.selectIdByUidAndBid(GetUidBySecurity.getUid(), addRecommendDto.getBid());
        if (n != null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你只能对同一书籍进行一次推荐");
        }
        UserRecommend userRecommend = new UserRecommend();
        userRecommend.setTitle(addRecommendDto.getTitle());
        userRecommend.setContent(addRecommendDto.getContent());
        userRecommend.setBid(addRecommendDto.getBid());
        userRecommend.setUid(GetUidBySecurity.getUid());
        return recommendMapper.insertSelective(userRecommend) != 0;
    }

    @Override
    public PageInfo<RecommendVo> getRecommends(int page, int pageSize) {
        Integer uid = GetUidBySecurity.getUid();
        PageHelper.startPage(page, pageSize);
        List<UserRecommend> recommendList = recommendMapper.selectByUid(uid);
        PageInfo pageInfo = new PageInfo(recommendList);
        List<RecommendVo> recommendVoList = new ArrayList<>();
        pageInfo.getList().forEach(recommend -> {
            Book book = bookMapper.selectByPrimaryKey(((UserRecommend) recommend).getBid());
            recommendVoList.add(UserModelConverter.recommendAndBookToRecommendVo(((UserRecommend) recommend), book));
        });
        pageInfo.setList(recommendVoList);
        return pageInfo;
    }

    @Override
    public String getAvatarUrl(Integer uid) {
        return userInfoMapper.selectAvatarUrlByUid(uid);
    }

}
