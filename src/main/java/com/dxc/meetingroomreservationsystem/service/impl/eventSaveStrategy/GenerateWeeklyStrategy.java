package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GenerateWeeklyStrategy extends AbstractEventSaveStrategy{

    @Override
    protected List<Event> generateRecurrenceDate(EventBo event, LocalTime startLocalTime, LocalTime endLocalTime, LocalDate fromDate, LocalDate toDate, List<List<Date>> recurrenceDate) {
        List<Integer> week = event.getWeek();
        Integer peerWeek = event.getWeekth();
        for(Integer w : week){
            setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,weekMap.get(w),peerWeek);
        }
        List<Event> recurrenceEvent = new ArrayList<>();
        for (List<Date> dates : recurrenceDate) {
            Event temp = new Event();
            BeanUtils.copyProperties(event,temp);
            temp.setStart(dates.get(0));
            temp.setEnd((dates.get(1)));
            temp.setId(null);
            recurrenceEvent.add(temp);
        }
        return recurrenceEvent;
    }
}
