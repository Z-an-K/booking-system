package com.dxc.meetingroomreservationsystem.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsUtil {

    private static UserDetailsUtil instance;
    private UserDetailsUtil(){

    }
    public static UserDetailsUtil getInstance() {
        if (instance == null) {
            instance = new UserDetailsUtil();
        }
        return instance;
    }
    public  UserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 获取用户信息
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails;
        }
        return null;
    }
}
