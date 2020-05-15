package com.jycz.consumer.service.impl;

import com.jycz.common.dao.RelationGroupMapper;
import com.jycz.common.dao.UserGroupMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.dao.UserRelationMapper;
import com.jycz.common.model.entity.RelationGroup;
import com.jycz.common.model.entity.UserGroup;
import com.jycz.common.model.entity.UserRelation;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.consumer.service.UserRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ling
 * @data 2020/4/9 10:19
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    private final UserMapper userMapper;
    private final RelationGroupMapper relationGroupMapper;
    private final UserRelationMapper userRelationMapper;
    private final UserGroupMapper userGroupMapper;

    public UserRelationServiceImpl(RelationGroupMapper relationGroupMapper, UserRelationMapper userRelationMapper, UserGroupMapper userGroupMapper, UserMapper userMapper) {
        this.relationGroupMapper = relationGroupMapper;
        this.userRelationMapper = userRelationMapper;
        this.userGroupMapper = userGroupMapper;
        this.userMapper = userMapper;
    }

    /**
     * 判断目标id是否存在
     * 首先获取分组id：判断分组是否存在，存在则获取id，不存在则对他进行插入获取自增长id
     * 判断用户与该分组的关系
     * 判断该用户之间是否存在某种关系，如已关注，已互相关注，已拉黑
     * 如果A关注B的时候B已关注A则将已关注转换为互相关注，如果已互相关注则直接返回，已拉黑则拒绝关注
     */
    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public boolean addFollow(Integer sourceId, Integer targetId, String groupName) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你要关注的用户不存在");
        }
        Integer userGroupId = this.getUgId(sourceId, groupName);
        //当前用户是否与目标用户发起过关系
        Boolean status = userRelationMapper.selectStatusByBothId(sourceId, targetId);
        if (status == null) {
            UserRelation userRelation = new UserRelation(sourceId, targetId, true, userGroupId);
            userRelationMapper.insertSelective(userRelation);
            return true;
        } else if (status) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已经关注过该用户了");
        } else {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "该用户是你的黑名单用户");
        }
    }

    @Override
    public boolean joinBlacklist(Integer sourceId, Integer targetId) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "该用户不存在");
        }
        String groupName = "黑名单";
        Integer userGroupId = this.getUgId(sourceId, groupName);
        Boolean status = userRelationMapper.selectStatusByBothId(sourceId, targetId);
        if (status == null) {
            UserRelation userRelation = new UserRelation(sourceId, targetId, false, userGroupId);
            userRelationMapper.insertSelective(userRelation);
            return true;
        } else if (status) {
            //该用户是关注用户
            userRelationMapper.updateRelationStatus(sourceId, targetId, false);
            return true;
        } else {
            //已经将该用户拉黑
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "该用户已经是你的黑名单用户了");
        }
    }

    @Override
    public boolean cancelFollow(Integer sourceId, Integer targetId) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "该用户不存在");
        }
        Boolean status = userRelationMapper.selectStatusByBothId(sourceId, targetId);
        if (status == null || !status) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你还没有关注该用户呢");
        } else {
            userRelationMapper.delete(sourceId, targetId);
            return true;
        }
    }

    @Override
    public boolean deleteFromBlacklist(Integer sourceId, Integer targetId) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "该用户不存在");
        }
        Boolean status = userRelationMapper.selectStatusByBothId(sourceId, targetId);
        if (status == null || status) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "用户之间不存在拉黑关系");
        } else {
            userRelationMapper.delete(sourceId, targetId);
            return true;
        }
    }

    /**
     * 获取分组id与代表用户与分组关系的ugid
     *
     * @param sourceId   用户id
     * @param groupName 组名
     * @return int
     */
    private Integer getUgId(Integer sourceId, String groupName) {
        Integer gid = relationGroupMapper.selectIdByName(groupName);
        if (gid == null) {
            RelationGroup relationGroup = new RelationGroup();
            relationGroup.setGroupName(groupName);
            relationGroupMapper.insertSelective(relationGroup);
            gid = relationGroup.getId();
        }
        //将用户与该分组建立关系
        Integer userGroupId = userGroupMapper.selectIdByUid(sourceId);
        if (userGroupId == null) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUid(sourceId);
            userGroup.setGid(gid);
            userGroupMapper.insertSelective(userGroup);
            userGroupId = userGroup.getId();
        }
        return userGroupId;
    }
}
