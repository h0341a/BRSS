package com.jycz.common.utils;

import com.jycz.common.model.dto.UserDto;
import com.jycz.common.model.entity.User;
import com.jycz.common.model.vo.UserVo;
import org.springframework.beans.BeanUtils;

public class LoginModelConverter {
    public static User userDtoToUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
    public static UserVo userToUserVo(User user){
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }
}
