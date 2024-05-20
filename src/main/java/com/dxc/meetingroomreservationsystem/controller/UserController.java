package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.PasswordValidator;
import com.dxc.meetingroomreservationsystem.pojo.User;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public R<UserDetails> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 获取用户信息
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return R.createBySuccess(userDetails);
        }
        return R.createByError();
    }

    @PutMapping
    public R<Boolean> save(@RequestBody  User user){
        int i = userService.saveUser(user);
        return i > 0 ? R.createBySuccess() : R.createByError();
    }

    @PostMapping("/validPassword")
    public R<Boolean> validPassword(@RequestBody PasswordValidator passwordValidator){
        return R.createBySuccess(userService.validPassword(passwordValidator));
    }

    @PostMapping("/changePassword")
    public R<Boolean> changePassword(@RequestBody User user){
        boolean b = userService.updatePasswordById(user);
        return b ? R.createBySuccess() : R.createByError();
    }

    @GetMapping("/validUsername")
    public R<Boolean> validUsername(@RequestParam String username){
        boolean b = userService.validUsername(username);
        return  R.createBySuccess(b);
    }
}

