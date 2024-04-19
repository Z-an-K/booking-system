package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.mapper.EventMapper;
import com.dxc.meetingroomreservationsystem.pojo.*;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.service.UserService;
import com.dxc.meetingroomreservationsystem.utils.TimeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Component
@Slf4j
@Setter
public abstract class AbstractEventSaveStrategy implements EventSaveStrategy {


    private EventMapper eventMapper;
    private UserService userService;
    private RoomService roomService;
    protected Map<Integer, DayOfWeek> weekMap;

    protected List<DayOfWeek> workday;
    public AbstractEventSaveStrategy(){
        weekMap = new HashMap<>();
        weekMap.put(1,DayOfWeek.MONDAY);
        weekMap.put(2,DayOfWeek.TUESDAY);
        weekMap.put(3,DayOfWeek.WEDNESDAY);
        weekMap.put(4,DayOfWeek.THURSDAY);
        weekMap.put(5,DayOfWeek.FRIDAY);
        weekMap.put(6,DayOfWeek.SATURDAY);
        weekMap.put(7,DayOfWeek.SUNDAY);
        workday = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
    }


    protected abstract List<Event> generateRecurrenceDate(EventBo event, LocalTime startLocalTime, LocalTime endLocalTime,
                                                          LocalDate fromDate, LocalDate toDate, List<List<Date>> recurrenceDate);
    @Override
    public boolean saveEvent(EventBo event) {
        //查询room id和 user id
        String username = event.getUsername();
        String roomNumber = event.getRoom();
        User user = null;
        Room room = null;

        user = userService.getUserByUserName(username);
        room = roomService.getRoomByNumber(roomNumber);


        Date startTime = event.getStartTime();
        Date endTime = event.getEndTime();
        LocalTime startLocalTime = TimeUtils.convertDate2LocalTime(startTime);
        LocalTime endLocalTime = TimeUtils.convertDate2LocalTime(endTime);
        LocalDate fromDate = null;
        LocalDate toDate = null;
        List<List<Date>> recurrenceDate = new ArrayList<>();
        if(event.getFrom() != null && event.getTo() != null){
            Date from = event.getFrom();
            Date to = event.getTo();

            fromDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            toDate = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }


        // 调用策略
        List<Event> recurrenceEvent = generateRecurrenceDate(event, startLocalTime, endLocalTime, fromDate, toDate, recurrenceDate);

        // 保存到db
        eventMapper.insertBatch(recurrenceEvent);
        List<Record> records = new ArrayList<>();
        for (Event eventItem : recurrenceEvent) {
            //userId,eventId,roomId
            records.add(new Record(user.getId(),eventItem.getId(),room.getId()));
        }
        //保存到记录表
        return this.eventMapper.saveRecords(records);
    }

    protected void setRecurrenceDateByWeekly(List<List<Date>> recurrenceDate,
                                           LocalDate fromDate,
                                           LocalDate toDate,
                                           LocalTime startLocalTime,
                                           LocalTime endLocalTime,
                                           DayOfWeek type,
                                           int peerWeek){
        LocalDate nextWeek = fromDate.with(TemporalAdjusters.nextOrSame(type));
        while (!nextWeek.isAfter(toDate)) {
            LocalDateTime startLocalDateTime = LocalDateTime.of(nextWeek, startLocalTime);
            Date startDate =  Date.from(startLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
            LocalDateTime endLocalDateTime = LocalDateTime.of(nextWeek, endLocalTime);
            Date endDate =  Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
            recurrenceDate.add(Arrays.asList(startDate,endDate));
            nextWeek = nextWeek.plusWeeks(peerWeek);
        }
    }

    protected void getWeeksInMonth(List<List<Date>> recurrenceDate,LocalDate fromDate, LocalDate toDate,List<DayOfWeek> days, int rank,LocalTime startLocalTime,
                                 LocalTime endLocalTime) {
        List<LocalDate> weeks = new ArrayList<>();
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusMonths(1)){
            switch(rank){
                case 1: {
                    for (DayOfWeek day : days) {
                        weeks.add(date.with(TemporalAdjusters.firstInMonth(day)));
                    }
                }break;
                case 2: {
                    for (DayOfWeek day : days) {
                        weeks.add(date.with(TemporalAdjusters.dayOfWeekInMonth(2, day)));
                    }
                }break;
                case 3:{
                    for (DayOfWeek day : days) {
                        weeks.add(date.with(TemporalAdjusters.dayOfWeekInMonth(3, day)));
                    }
                }break;
                case 4:{
                    for (DayOfWeek day : days) {
                        weeks.add(date.with(TemporalAdjusters.dayOfWeekInMonth(4, day)));
                    }
                }break;
                case 5:{
                    for (DayOfWeek day : days) {
                        weeks.add(date.with(TemporalAdjusters.lastInMonth(day)));
                    }
                }

            }
            for (LocalDate week : weeks) {
                LocalDateTime startLocalDateTime = LocalDateTime.of(week, startLocalTime);
                Date startDate =  Date.from(startLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
                LocalDateTime endLocalDateTime = LocalDateTime.of(week, endLocalTime);
                Date endDate =  Date.from(endLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
                recurrenceDate.add(Arrays.asList(startDate,endDate));
            }

        }
    }
}
