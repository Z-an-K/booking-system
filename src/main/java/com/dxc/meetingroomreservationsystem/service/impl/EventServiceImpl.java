package com.dxc.meetingroomreservationsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxc.meetingroomreservationsystem.mapper.EventMapper;
import com.dxc.meetingroomreservationsystem.pojo.*;
import com.dxc.meetingroomreservationsystem.pojo.enums.RecurrencePatternEnum;
import com.dxc.meetingroomreservationsystem.pojo.enums.WeekEnum;
import com.dxc.meetingroomreservationsystem.pojo.enums.WeekTypeEnum;
import com.dxc.meetingroomreservationsystem.service.EventService;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy.AbstractEventSaveStrategy;
import com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy.EventSaveStrategy;
import com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy.EventSaveStrategyFactory;
import com.dxc.meetingroomreservationsystem.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Consumer;

@Service
@Slf4j
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private EventSaveStrategyFactory factory;

    @Override
    public List<EventItem> getEventByRange(String start, String end) {
        List<EventDto> eventsByRange = eventMapper.getEventsByRange(start, end);
        //changeType
        List<EventItem> events = new ArrayList<>();
        eventsByRange.forEach(eventDto -> events.add(new EventItem(eventDto)));
        return events;
    }
    @Override
    @Transactional
    public boolean saveEvent(EventBo event) {
        Boolean recurrence = event.isRecurrence();
        Integer recurrencePattern = event.getRecurrencePattern();
        AbstractEventSaveStrategy strategy = factory.CreateInstance(recurrence, recurrencePattern);
        try{
            strategy.saveEvent(event);
            return true;
        }catch (Exception e){
            log.error("save event error",e);
        }
        return false;
    }

    /**
     *
     *
     * */



}
