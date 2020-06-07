package com.jycz.consumer.utils;


import com.jycz.common.model.entity.*;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.vo.UserVo;
import com.jycz.consumer.model.vo.DynamicVo;
import com.jycz.consumer.model.vo.RecommendVo;
import com.jycz.consumer.model.vo.UserInfoVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;

import javax.xml.crypto.Data;
import javax.xml.stream.events.Comment;
import java.util.*;

/**
 * @author ling
 * @data 2020/4/8 12:43
 */
public class UserModelConverter {
    public static List<DynamicVo> mergeMultiList(List<UserRecommend> userRecommendList, List<Star> starList,
                                                 List<UserCollection> collectionList, List<UserComment> commentList) {
        List<DynamicVo> dynamicVoList = new ArrayList<>();
        userRecommendList.forEach(recommend -> {
            DynamicVo dynamicVo = new DynamicVo("recommend", "新增了一个推荐",
                    recommend.getId() + "", recommend.getRecommendDate());
            dynamicVoList.add(dynamicVo);
        });
        starList.forEach(star -> {
            DynamicVo dynamicVo = new DynamicVo("star", "点了一个赞",
                    star.getRid() + "", star.getCreateDate());
            dynamicVoList.add(dynamicVo);
        });
        collectionList.forEach(collection -> {
            DynamicVo dynamicVo = new DynamicVo("collection", "新收藏了一本书",
                    collection.getBid() + "", collection.getCollectionDate());
            dynamicVoList.add(dynamicVo);
        });
        commentList.forEach(comment -> {
            DynamicVo dynamicVo = new DynamicVo("comment", "新增了一个评论",
                    comment.getRid() + "", comment.getCommentDate());
            dynamicVoList.add(dynamicVo);
        });
        Collections.sort(dynamicVoList, new Comparator<DynamicVo>() {
            @Override
            public int compare(DynamicVo dynamicVo, DynamicVo t1) {
                if (dynamicVo.getShowDate().after(t1.getShowDate())){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

        return dynamicVoList;
    }

    public static Book recommendDtoToBook(Integer uid, RecommendDto recommendDto) {
        Book book = new Book();
        book.setCoverUrl(recommendDto.getCoverUrl());
        book.setName(recommendDto.getBookName());
        book.setAuthor(recommendDto.getBookAuthor());
        book.setCommitUser(uid);
        return book;
    }

    public static UserRecommend recommendDtoToRecommend(Integer uid, Integer bid, RecommendDto recommendDto) {
        UserRecommend userRecommend = new UserRecommend();
        userRecommend.setTitle(recommendDto.getTitle());
        userRecommend.setContent(recommendDto.getContent());
        userRecommend.setUid(uid);
        userRecommend.setBid(bid);
        return userRecommend;
    }

    public static RecommendVo recommendAndBookToRecommendVo(UserRecommend recommend, Book book) {
        RecommendVo recommendVo = new RecommendVo();
        BeanUtils.copyProperties(recommend, recommendVo);
        recommendVo.setCoverUrl(book.getCoverUrl());
        recommendVo.setBookName(book.getName());
        recommendVo.setAuthor(book.getAuthor());
        return recommendVo;
    }

    public static UserInfoVo userInfoToUserInfoVo(UserInfo userInfo, User user) {
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        userInfoVo.setNickname(user.getNickname());
        userInfoVo.setEmail(user.getEmail());
        return userInfoVo;
    }

}
