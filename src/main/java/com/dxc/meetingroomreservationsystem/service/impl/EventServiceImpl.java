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
import com.dxc.meetingroomreservationsystem.utils.UserDetailsUtil;
import jdk.nashorn.internal.objects.annotations.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.FaultAction;
import java.awt.print.Book;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

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
        return saveOrUpdateEvent(event);
    }

    @Override
    @Transactional
    public boolean updateEvent(EventBo eventBo) {
        List<Integer> ids = this.eventMapper.getEventIdByGroupId(eventBo.getGroupId());
        //List<Integer> ids = events.stream().mapToInt(Event::getId).boxed().collect(Collectors.toList());
        this.removeBatchByIds(ids);
        this.eventMapper.deleteByEventIds(ids);
        return saveOrUpdateEvent(eventBo);
    }

    @Override
    public List<BookingTable> getEventByUser(int userId,String location,String roomNumber) {
        try{
            List<EventDto> events = this.eventMapper.getEventByUser(userId,location,roomNumber);
            Set<String> seen = new HashSet<>();
            List<EventDto> distinctEvents = events.stream()
                    .filter(e -> seen.add(e.getGroupId()))
                    .collect(Collectors.toList());
            //List<EventDto> collect = events.stream().distinct().collect(Collectors.toList());
            ArrayList<BookingTable> bookingTables = new ArrayList<>();
            distinctEvents.forEach(eventDto -> bookingTables.add(new BookingTable(eventDto)));
            return bookingTables;

        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteByGroupId(String groupId){
        LambdaQueryWrapper<Event> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Event::getGroupId , groupId);
        try{
            List<Event> list = this.list(wrapper);
            List<Integer> ids = list.stream().mapToInt(Event::getId).boxed().collect(Collectors.toList());
            this.eventMapper.deleteByEventIds(ids);
            return this.remove(wrapper);
        }catch (Exception e){
            log.error("delete record error",e);
        }
        return false;
    }

    public boolean saveOrUpdateEvent(EventBo eventBo) {
        Boolean recurrence = eventBo.isRecurrence();
        Integer recurrencePattern = eventBo.getRecurrencePattern();
        AbstractEventSaveStrategy strategy = factory.CreateInstance(recurrence, recurrencePattern);

        try{
            strategy.saveEvent(eventBo);
            return true;
        }catch (Exception e){
            log.error("save event error",e);
        }
        return false;
    }
}


