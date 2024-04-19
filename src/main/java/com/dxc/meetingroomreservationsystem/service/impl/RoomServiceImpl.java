package com.dxc.meetingroomreservationsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxc.meetingroomreservationsystem.mapper.RoomMapper;
import com.dxc.meetingroomreservationsystem.pojo.Room;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private  RoomMapper roomMapper;

    public Room getRoomByNumber(String roomNumber){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Room::getRoomId,roomNumber);
        return roomMapper.selectOne(wrapper);
    }



}
