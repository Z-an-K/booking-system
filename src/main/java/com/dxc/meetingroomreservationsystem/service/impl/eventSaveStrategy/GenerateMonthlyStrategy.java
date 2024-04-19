package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;
import com.dxc.meetingroomreservationsystem.pojo.enums.WeekEnum;
import com.dxc.meetingroomreservationsystem.pojo.enums.WeekTypeEnum;
import org.springframework.beans.BeanUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GenerateMonthlyStrategy extends AbstractEventSaveStrategy{
    @Override
    protected List<Event> generateRecurrenceDate(EventBo event, LocalTime startLocalTime, LocalTime endLocalTime, LocalDate fromDate, LocalDate toDate, List<List<Date>> recurrenceDate) {
        int peerWeek = event.getWeekth();
        if(event.getWeekType() == WeekTypeEnum.Day.getWeekType()){
            //每天
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime, DayOfWeek.MONDAY,peerWeek);
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,DayOfWeek.TUESDAY,peerWeek);
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,DayOfWeek.WEDNESDAY,peerWeek);
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,DayOfWeek.THURSDAY,peerWeek);
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,DayOfWeek.FRIDAY,peerWeek);
            }else {
                setRecurrenceDateByWeekly(recurrenceDate,fromDate,toDate,startLocalTime,endLocalTime,weekMap.get(event.getWeek().get(0)),peerWeek);
            }
        }else if(event.getWeekType() == WeekTypeEnum.first.getWeekType()){
            //第一周
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                getWeeksInMonth(recurrenceDate,fromDate, toDate, workday, 1, startLocalTime, endLocalTime);
            }else {
                getWeeksInMonth(recurrenceDate,fromDate, toDate, Arrays.asList(weekMap.get(event.getWeek().get(0))), 1, startLocalTime, endLocalTime);
            }
        }else if(event.getWeekType() == WeekTypeEnum.second.getWeekType()){
            //第二周
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                getWeeksInMonth(recurrenceDate,fromDate, toDate, workday, 2, startLocalTime, endLocalTime);
            }else {
                getWeeksInMonth(recurrenceDate,fromDate, toDate, Arrays.asList(weekMap.get(event.getWeek().get(0))), 2, startLocalTime, endLocalTime);
            }
        }else if(event.getWeekType() == WeekTypeEnum.third.getWeekType()){
            //第三周
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                getWeeksInMonth(recurrenceDate,fromDate, toDate, workday, 3, startLocalTime, endLocalTime);
            }else {
                getWeeksInMonth(recurrenceDate,fromDate, toDate, Arrays.asList(weekMap.get(event.getWeek().get(0))), 3, startLocalTime, endLocalTime);
            }
        }else if (event.getWeekType() == WeekTypeEnum.forth.getWeekType()){
            //第四周
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                getWeeksInMonth(recurrenceDate,fromDate, toDate, workday, 4, startLocalTime, endLocalTime);
            }else {
                getWeeksInMonth(recurrenceDate,fromDate, toDate, Arrays.asList(weekMap.get(event.getWeek().get(0))), 4, startLocalTime, endLocalTime);
            }
        }else{
            if(event.getWeek().get(0) == WeekEnum.workDay.getWeekCode()){
                //工作日
                getWeeksInMonth(recurrenceDate,fromDate, toDate, workday, 5, startLocalTime, endLocalTime);
            }else {
                getWeeksInMonth(recurrenceDate,fromDate, toDate, Arrays.asList(weekMap.get(event.getWeek().get(0))), 5, startLocalTime, endLocalTime);
            }
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
