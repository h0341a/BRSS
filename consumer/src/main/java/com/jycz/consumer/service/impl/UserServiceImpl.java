package com.jycz.consumer.service.impl;

import com.jycz.common.dao.RoleMapper;
import com.jycz.common.dao.UserInfoMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserInfo;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.consumer.model.dto.UserDto;
import com.jycz.consumer.model.vo.UserVo;
import com.jycz.consumer.service.UserService;
import com.jycz.consumer.utils.UserModelConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ling
 * @data 2020/4/8 11:29
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final RoleMapper roleMapper;
    public UserServiceImpl(UserMapper userMapper, UserInfoMapper userInfoMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public boolean usernameIsSave(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public UserVo userRegister(UserDto userDto) throws BusinessException {
        if (userMapper.selectByUsername(userDto.getUsername()) != null){
            throw new BusinessException(ErrCodeEnum.PARAMETERS_VALIDATION_FAIL, "该用户名已存在");
        }
        Integer rid = roleMapper.selectIdByRoleName("user");
        if (rid == null){
            throw new BusinessException(ErrCodeEnum.DATA_ABORT, "该用户注册的角色不存在");
        }
        User user = UserModelConverter.userDtoToUser(userDto);
        user.setRoleId(rid);
        userMapper.insertSelective(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoMapper.insertSelective(userInfo);
        return UserModelConverter.userToUserVo(user);
    }
}
