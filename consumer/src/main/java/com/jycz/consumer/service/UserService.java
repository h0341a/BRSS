package com.jycz.consumer.service;

import com.jycz.common.response.BusinessException;
import com.jycz.consumer.model.dto.RecommendDto;

/**
 * @author ling
 * @data 2020/4/8 11:25
 */
public interface UserService {

    /**
     * 添加图书推荐
     * @param uid 用户id
     * @param recommendDto 相关信息
     * @return 是否成功
     */
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException;

    /**
     * 获取用户信息
     * @param uid 用户id
     * @return 用户信息视图
     */
    public String getAvatarUrl(Integer uid);


}
