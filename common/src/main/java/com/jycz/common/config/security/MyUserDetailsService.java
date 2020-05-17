package com.jycz.common.config.security;

import com.jycz.common.dao.RoleMapper;
import com.jycz.common.dao.UserMapper;
import com.jycz.common.model.entity.Role;
import com.jycz.common.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public MyUserDetailsService(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return new LoginUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
