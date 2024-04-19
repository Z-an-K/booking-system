package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    private R<UserDetails> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 获取用户信息
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return R.createBySuccess(userDetails);
        }
        return R.createByError();
    }

    @PutMapping
    public int save(@RequestBody  User user){
        return userService.saveUser(user);
    }
}
