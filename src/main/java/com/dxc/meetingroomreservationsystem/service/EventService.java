package com.dxc.meetingroomreservationsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxc.meetingroomreservationsystem.pojo.*;

import java.util.Date;
import java.util.List;

public interface EventService extends IService<Event> {

    List<EventItem> getEventByRange(String start, String end);

    boolean saveEvent(EventBo event);
    boolean updateEvent(EventBo eventBo);

    List<BookingTable> getEventByUser(int userId,String location,String roomNumber);

    boolean deleteByGroupId(String groupId);
}
