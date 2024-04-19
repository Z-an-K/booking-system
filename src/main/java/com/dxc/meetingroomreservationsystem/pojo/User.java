package com.dxc.meetingroomreservationsystem.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
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
public class User extends BasePojo implements Serializable {

    private static final long serialVersionUID = -7055272280732801364L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role;

    private Collection<? extends GrantedAuthority> authorities;

}
