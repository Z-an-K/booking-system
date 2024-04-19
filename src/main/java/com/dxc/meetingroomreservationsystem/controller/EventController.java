package com.dxc.meetingroomreservationsystem.controller;

import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;
import com.dxc.meetingroomreservationsystem.pojo.EventDto;
import com.dxc.meetingroomreservationsystem.pojo.EventItem;
import com.dxc.meetingroomreservationsystem.service.EventService;
import com.dxc.meetingroomreservationsystem.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
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

}
