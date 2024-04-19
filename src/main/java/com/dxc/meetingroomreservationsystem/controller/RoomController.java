package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.Room;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @GetMapping("/getRooms")
    public R<List<Room>> getRooms(){
        List<Room> list = roomService.list();
        return R.createBySuccess(list);
    }

    @PutMapping("/saveRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public R saveRoom(Room room){
        boolean save = roomService.save(room);
        return save ? R.createBySuccess():R.createByError();
    }

    @PostMapping("/updateRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public R updateRoom(@RequestBody Room room){
        boolean update = roomService.updateById(room);
        return update ?  R.createBySuccess():R.createByError();
    }

    @DeleteMapping("/delRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public R delRoom(List<Integer> ids){
        boolean remove = roomService.removeBatchByIds(ids);
        return remove ?  R.createBySuccess():R.createByError();
    }
}
