package com.dxc.meetingroomreservationsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxc.meetingroomreservationsystem.pojo.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
}
