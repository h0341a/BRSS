package com.jycz.common.service.impl;

import com.jycz.common.dao.RoleMapper;
import com.jycz.common.dao.UserInfoMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.entity.UserInfo;
import com.jycz.common.model.vo.UserVo;
import com.jycz.common.response.BusinessException;
import com.jycz.common.response.ErrCodeEnum;
import com.jycz.common.service.LoginService;
import com.jycz.common.utils.LoginModelConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserInfoMapper userInfoMapper;

    public LoginServiceImpl(UserMapper userMapper, RoleMapper roleMapper, UserInfoMapper userInfoMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userInfoMapper = userInfoMapper;
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
        Integer rid = roleMapper.selectIdByRoleName("ROLE_USER");
        if (rid == null){
            throw new BusinessException(ErrCodeEnum.DATA_ABORT, "该用户注册的角色不存在");
        }
        User user = LoginModelConverter.userDtoToUser(userDto);
        user.setRoleId(rid);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insertSelective(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfoMapper.insertSelective(userInfo);
        return LoginModelConverter.userToUserVo(user);
    }
}
