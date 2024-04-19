package com.dxc.meetingroomreservationsystem.configuration.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dxc.meetingroomreservationsystem.utils.UserDetailsUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        UserDetails userDetails = UserDetailsUtil.getInstance().getUserDetails();
        String username = userDetails.getUsername();
        this.strictInsertFill(metaObject,"createBy",String.class,username);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date()); // 自动填充createTime
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDetails userDetails = UserDetailsUtil.getInstance().getUserDetails();
        String username = userDetails.getUsername();
        this.strictInsertFill(metaObject,"createBy",String.class,username);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 自动填充updateTime
    }
}
