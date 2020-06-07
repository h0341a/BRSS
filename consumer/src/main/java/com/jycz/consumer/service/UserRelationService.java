package com.jycz.consumer.service;

import com.jycz.common.response.BusinessException;

/**
 * @author ling
 * @data 2020/4/9 10:16
 */
public interface UserRelationService {
    /**
     * 根据用户id添加关注
     * @param sourceId 发起者id
     * @param targetId 目标id
     * @return 是否成功
     */
    public boolean addFollow(Integer sourceId, Integer targetId) throws BusinessException;

    /**
     * 取消某个用户的关注
     * @param sourceId 发起者id
     * @param targetId 目标id
     * @return 是否取消成功
     */
    public boolean cancelFollow(Integer sourceId, Integer targetId) throws BusinessException;

    public boolean hasRelation(Integer targetId) throws BusinessException;
}
