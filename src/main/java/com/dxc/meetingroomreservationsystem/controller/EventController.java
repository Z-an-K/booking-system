package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.*;
import com.dxc.meetingroomreservationsystem.service.EventService;
import com.dxc.meetingroomreservationsystem.utils.R;
import com.dxc.meetingroomreservationsystem.utils.UserDetailsUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @GetMapping("/getEventByRange")
    public R<List<EventItem>> getEventByRange(String start, String end){
        List<EventItem> events = eventService.getEventByRange(start, end);
        return R.createBySuccess(events);
    }

    @PutMapping("/saveEvent")
    public R<Boolean> saveEvent(@RequestBody EventBo event){
        boolean res = eventService.saveEvent(event);

        return res ? R.createBySuccess("Booking Success") : R.createByError("Booking Error");
    }

    @PostMapping("/updateEvent")
    public R<Boolean> updateEvent(@RequestBody EventBo event){
        boolean res = eventService.updateEvent(event);
        return res ? R.createBySuccess("Update Success") : R.createByError("Update Error");
    }

    @GetMapping("/getEventByUser")
    public R<List<BookingTable>> getEventByUser(@RequestParam Integer userId,
                                                @RequestParam(required = false) String location,
                                                @RequestParam(required = false) String roomNumber){
        List<BookingTable> eventByUser = eventService.getEventByUser(userId,location,roomNumber);
        return eventByUser != null ? R.createBySuccess(eventByUser):R.createByError();
    }

    @DeleteMapping("/deleteEventByGroupId")
    public R<Boolean> deleteByGroupId(String groupId){
        boolean delete = eventService.deleteByGroupId(groupId);
        return delete ? R.createBySuccess("delete success") : R.createByError("delete error");
    }
}

