package com.jycz.user.service.impl;

import com.jycz.common.dao.RelationGroupMapper;
import com.jycz.common.dao.UserGroupMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.dao.UserRelationMapper;
import com.jycz.common.model.entity.RelationGroup;
import com.jycz.common.model.entity.UserGroup;
import com.jycz.common.model.entity.UserRelation;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.user.service.UserRelationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Relation;

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
    public boolean addFollow(Integer uid, Integer targetId, String groupName) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null){
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你要关注的用户不存在");
        }
        Integer gid = relationGroupMapper.selectIdByName(groupName);
        if (gid == null) {
            RelationGroup relationGroup = new RelationGroup();
            relationGroup.setGroupName(groupName);
            relationGroupMapper.insertSelective(relationGroup);
            gid = relationGroup.getId();
        }
        //将用户与该分组建立关系
        Integer userGroupId = userGroupMapper.selectIdByUid(uid);
        if (userGroupId == null){
            UserGroup userGroup = new UserGroup();
            userGroup.setUid(uid);
            userGroup.setGid(gid);
            userGroupMapper.insertSelective(userGroup);
            userGroupId = userGroup.getId();
        }
        //当前用户是否与目标用户发起过关系
        Integer status = userRelationMapper.selectStatusByUidAndIdolId(uid, targetId);
        if (status != null) {
            //用户被来黑
            if (status == 0) {
                throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "关注失败,你已将该用户拉黑");
            }
            //已关注或相互关注
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已经关注过该用户了");
        } else {
            //目标用户是否与当前用户发起过关系
            status = userRelationMapper.selectStatusByUidAndIdolId(targetId, uid);
            if (status != null) {
                if (status == 0) {
                    throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "关注失败,你已被该用户拉黑");
                } else if (status == 1) {
                    //升级为互相关注
                    if (userRelationMapper.updateToEachOther(targetId, uid) == 1) {
                        return true;
                    }
                } else {
                    //互相关注
                    throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已经在关注他了");
                }
            } else {
                UserRelation userRelation = new UserRelation();
                userRelation.setUid(uid);
                userRelation.setIdolId(targetId);
                userRelation.setStatus(1);
                userRelation.setUgid(userGroupId);
                if (userRelationMapper.insertSelective(userRelation) == 1) {
                    return true;
                }
            }
        }
        System.out.println("test3");
        return false;
    }

    @Override
    public void joinBlacklist(Integer uid, Integer targetId) {

    }

    @Override
    public void cancelFollow(Integer uid, Integer targetId) {

    }

    @Override
    public void deleteFromBlacklist(Integer uid, Integer targetId) {

    }
}
