package com.jycz.consumer.service.impl;

import com.jycz.common.dao.UserInfoMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.dao.UserMessageMapper;
import com.jycz.common.dao.UserRelationMapper;
import com.jycz.common.model.entity.*;
import com.jycz.common.model.vo.UserVo;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.utils.GetUidBySecurity;
import com.jycz.consumer.model.dto.MsgDto;
import com.jycz.consumer.model.vo.FriendListVo;
import com.jycz.consumer.model.vo.FriendVo;
import com.jycz.consumer.model.vo.MsgVo;
import com.jycz.consumer.service.UserRelationService;
import com.jycz.consumer.utils.UserModelConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling
 * @data 2020/4/9 10:19
 */
@Service
public class UserRelationServiceImpl implements UserRelationService {

    private final UserMapper userMapper;
    private final UserRelationMapper userRelationMapper;
    private final UserMessageMapper msgMapper;
    private final UserInfoMapper infoMapper;


    public UserRelationServiceImpl(UserRelationMapper userRelationMapper, UserMapper userMapper, UserMessageMapper userMessageMapper, UserInfoMapper infoMapper) {
        this.userRelationMapper = userRelationMapper;
        this.userMapper = userMapper;
        this.msgMapper = userMessageMapper;
        this.infoMapper = infoMapper;
    }

    @Override
    public FriendListVo getFriendList() {
        Integer uid = GetUidBySecurity.getUid();
        List<Integer> userFollowIds = userRelationMapper.selectTargetIdBySourceId(uid);
        List<Integer> followUserIds = userRelationMapper.selectSourceIdByTargetId(uid);
        followUserIds.removeAll(userFollowIds);
        List<FriendVo> userFollowList = this.idsConvertEntity(userFollowIds);
        List<FriendVo> followUserList = this.idsConvertEntity(followUserIds);
        return new FriendListVo(userFollowList, followUserList);
    }

    private List<FriendVo> idsConvertEntity(List<Integer> ids) {
        List<FriendVo> friendList = new ArrayList<>();
        ids.forEach(id -> {
            User user = userMapper.selectByPrimaryKey(id);
            UserInfo userInfo = infoMapper.selectByUid(id);
            Boolean flag = msgMapper.selectUnreadMsg(id, GetUidBySecurity.getUid()) != 0;
            friendList.add(UserModelConverter.userToFriendVo(user, userInfo.getAvatarUrl(), flag));
        });
        return friendList;
    }

    @Override
    public boolean sendMsg(MsgDto msgDto) throws BusinessException {
        if (userMapper.selectByPrimaryKey(msgDto.getTargetId()) == null) {
            throw new BusinessException(ErrCodeEnum.DATA_ABORT, "目标用户不存在");
        }
        UserMessage userMessage = new UserMessage(GetUidBySecurity.getUid(), msgDto.getTargetId(), msgDto.getContent());
        return msgMapper.insertSelective(userMessage) != 0;
    }

    @Override
    public List<MsgVo> getMsgFromSb(Integer targetId) throws BusinessException {
        Integer uid = GetUidBySecurity.getUid();
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.DATA_ABORT, "目标用户不存在");
        }
        List<UserMessage> userMessageList = msgMapper.selectMsgByUid(uid, targetId);
        List<MsgVo> msgVoList = new ArrayList<>();
        userMessageList.forEach(userMessage -> {
            MsgVo msgVo = new MsgVo();
            BeanUtils.copyProperties(userMessage, msgVo);
            msgVo.setMyself(userMessage.getSendUid().equals(uid));
            msgVoList.add(msgVo);
        });
        return msgVoList;
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
    public boolean addFollow(Integer sourceId, Integer targetId) throws BusinessException {
        if (userMapper.selectByPrimaryKey(targetId) == null) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你要关注的用户不存在");
        }
        //当前用户是否与目标用户发起过关系
        Boolean hasRelation = userRelationMapper.selectStatusByBothId(sourceId, targetId);
        if (hasRelation == null) {
            hasRelation = false;
        }
        if (hasRelation) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "你已经关注过该用户了");
        }
        UserRelation userRelation = new UserRelation(sourceId, targetId, true);
        return userRelationMapper.insertSelective(userRelation) != 0;
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
    public boolean hasRelation(Integer targetId) throws BusinessException {
        if (targetId.equals(GetUidBySecurity.getUid())) {
            throw new BusinessException(ErrCodeEnum.USER_OPERATION_PUZZLE, "自己不能关注自己");
        }
        Boolean relation = userRelationMapper.selectStatusByBothId(GetUidBySecurity.getUid(), targetId);
        if (relation == null) {
            return false;
        } else {
            return relation;
        }
    }

}
