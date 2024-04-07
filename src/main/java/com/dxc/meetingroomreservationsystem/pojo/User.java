package com.dxc.meetingroomreservationsystem.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@TableName("tb_users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String createdBy;
    private Date createTime;
    private String updatedBy;
    private Date updateTime;

    private Collection<? extends GrantedAuthority> authorities;

}
