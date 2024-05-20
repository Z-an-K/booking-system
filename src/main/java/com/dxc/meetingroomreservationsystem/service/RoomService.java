package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxc.meetingroomreservationsystem.pojo.Room;

import java.util.List;

public interface RoomService extends IService<Room> {

    Room getRoomByNumber(String roomNumber);

    List<Room> getRoomList(Room room);
}
