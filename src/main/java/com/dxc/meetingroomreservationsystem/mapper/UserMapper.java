package com.dxc.meetingroomreservationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxc.meetingroomreservationsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from tb_users where  username=#{username}")
    User getUserByUserName(String userName);

}
