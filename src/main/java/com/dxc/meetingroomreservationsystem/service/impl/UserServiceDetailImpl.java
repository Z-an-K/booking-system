package com.dxc.meetingroomreservationsystem.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dxc.meetingroomreservationsystem.mapper.UserMapper;
import com.dxc.meetingroomreservationsystem.pojo.MyUser;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.pojo.enums.UserRoleEnums;
import com.dxc.meetingroomreservationsystem.service.UserService;
import org.apache.ibatis.javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserServiceDetailImpl implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mapper.getUserByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException(username + " not fond");
        }

        MyUser myUser = MyUser.createMyUser(user);

        if(StringUtils.isNotBlank(user.getRole())){
            myUser.getAuthorities().add(new SimpleGrantedAuthority(user.getRole()));
        }

        return myUser;

    }
}
