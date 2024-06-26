package com.dxc.meetingroomreservationsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxc.meetingroomreservationsystem.mapper.RoomMapper;
import com.dxc.meetingroomreservationsystem.pojo.Room;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private  RoomMapper roomMapper;

    public Room getRoomByNumber(String roomNumber){
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Room::getRoomId,roomNumber);
        return roomMapper.selectOne(wrapper);
    }

    @Override
    public List<Room> getRoomList(Room room) {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(room.getLocation()), Room::getLocation ,room.getLocation());
        wrapper.eq(StringUtils.isNotBlank(room.getRoomId()), Room::getRoomId,room.getRoomId());
        wrapper.like(StringUtils.isNotBlank(room.getEquipment()), Room::getEquipment,room.getEquipment());
        wrapper.eq(room.getStatus() != null, Room::getStatus,room.getStatus());
        return this.list(wrapper);
    }
}

