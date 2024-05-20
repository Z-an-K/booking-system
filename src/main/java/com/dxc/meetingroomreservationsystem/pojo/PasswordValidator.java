package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class PasswordValidator {

    private PasswordEncoder passwordEncoder;
    private String oldPassword;

    private String hashPassword;
    public PasswordValidator(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public boolean validate(){
        return passwordEncoder.matches(oldPassword,hashPassword);
    }
}
