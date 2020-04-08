package com.jycz.user.utils;

import com.jycz.common.model.entity.User;
import com.jycz.user.model.dto.UserDto;
import com.jycz.user.model.vo.UserVo;
import org.springframework.beans.BeanUtils;

/**
 * @author ling
 * @data 2020/4/8 12:43
 */
public class MyModelConverter {
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
