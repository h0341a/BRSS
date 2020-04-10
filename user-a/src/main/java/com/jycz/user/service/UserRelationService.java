package com.jycz.user.service;

import com.jycz.common.response.BusinessException;

/**
 * @author ling
 * @data 2020/4/9 10:16
 */
public interface UserRelationService {
    /**
     * 根据用户id添加关注
     * @param uid 用户登录的用户id
     * @param targetId 目标id
     * @param groupName 分配的组名，默认为未分组
     * @return 是否成功
     */
    public boolean addFollow(Integer uid, Integer targetId, String groupName) throws BusinessException;

    /**
     * 根据用户id拉入黑名单
     * @param uid 当前登录的用户id
     * @param targetId 目标id
     * @return 是否加入成功
     */
    public boolean joinBlacklist(Integer uid, Integer targetId) throws BusinessException;
    /**
     * 取消某个用户的关注
     * @param uid 当前用户id
     * @param targetId 目标id
     */
    public void cancelFollow(Integer uid, Integer targetId) throws BusinessException;
    /**
     * 取消某个用户的被拉黑
     * @param uid 当前用户id
     * @param targetId 目标id
     */
    public boolean deleteFromBlacklist(Integer uid, Integer targetId) throws BusinessException;
}
