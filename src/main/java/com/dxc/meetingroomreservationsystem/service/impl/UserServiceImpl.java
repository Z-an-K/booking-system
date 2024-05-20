package com.dxc.meetingroomreservationsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxc.meetingroomreservationsystem.mapper.UserMapper;
import com.dxc.meetingroomreservationsystem.pojo.PasswordValidator;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public int saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public boolean validPassword(PasswordValidator passwordValidator) {
        return passwordValidator.validate();
    }

    @Override
    public boolean updatePasswordById(User user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId,user.getId());
        wrapper.set(User::getPassword,passwordEncoder.encode(user.getPassword()));
        try {
            return this.update(wrapper);
        }catch (Throwable t){
            log.error("can not change password",t);
        }
        return false;
    }

    @Override
    public boolean validUsername(String username) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUsername,username);
        return this.getOne(wrapper) == null;
    }
}

