package com.jycz.consumer.service;

import com.github.pagehelper.PageInfo;
import com.jycz.consumer.model.vo.DynamicVo;
import com.jycz.common.response.BusinessException;
import com.jycz.consumer.model.dto.RecommendDto;
import com.jycz.consumer.model.dto.UserInfoDto;
import com.jycz.consumer.model.vo.RecommendVo;
import com.jycz.consumer.model.vo.UserInfoVo;

import java.util.List;

/**
 * @author ling
 * @data 2020/4/8 11:25
 */
public interface UserService {
    public List<DynamicVo> getUserDynamic(Integer uid);
    public UserInfoVo getUserInfo();
    public UserInfoVo getUserInfo(Integer uid);
    public boolean updateUserInfo(UserInfoDto userInfoDto);

    public boolean addOrDelCollection(Integer bid, int option);

    public boolean addOrDelStar(Integer rid, int option);

    public boolean isCollection(Integer bid);

    public boolean isStar(Integer rid);

    /**
     * 添加图书推荐
     *
     * @param uid          用户id
     * @param recommendDto 相关信息
     * @return 是否成功
     */
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException;

    /**
     * 获取用户创建的推荐的列表
     */
    public PageInfo<RecommendVo> getRecommends(int page, int pageSize);

    /**
     * 获取用户头像
     *
     * @param uid 用户id
     * @return 用户头像
     */
    public String getAvatarUrl(Integer uid);


}
