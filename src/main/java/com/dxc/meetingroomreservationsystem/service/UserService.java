package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxc.meetingroomreservationsystem.pojo.PasswordValidator;
import com.dxc.meetingroomreservationsystem.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {

    User getUserByUserName(String userName);

    int saveUser(User user);

    boolean validPassword(PasswordValidator passwordValidator);

    boolean updatePasswordById(User user);

    boolean validUsername(String username);
}
