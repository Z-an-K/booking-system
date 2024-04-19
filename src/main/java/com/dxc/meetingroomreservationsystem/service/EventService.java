package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;
import com.dxc.meetingroomreservationsystem.pojo.EventDto;
import com.dxc.meetingroomreservationsystem.pojo.EventItem;

import java.util.Date;
import java.util.List;

public interface EventService extends IService<Event> {

    List<EventItem> getEventByRange(String start, String end);

    boolean saveEvent(EventBo event);
}
