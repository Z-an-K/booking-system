package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxc.meetingroomreservationsystem.pojo.Room;

public interface RoomService extends IService<Room> {

    Room getRoomByNumber(String roomNumber);
}
