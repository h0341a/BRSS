package com.jycz.common.utils;

import com.jycz.common.config.security.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetUidBySecurity {
    public static Integer getUid(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((LoginUser) principal).getUid();
        }
        return null;
    }
}
