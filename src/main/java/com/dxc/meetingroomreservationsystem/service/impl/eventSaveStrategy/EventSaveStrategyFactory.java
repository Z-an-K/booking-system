package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.mapper.EventMapper;
import com.dxc.meetingroomreservationsystem.pojo.enums.RecurrencePatternEnum;
import com.dxc.meetingroomreservationsystem.service.RoomService;
import com.dxc.meetingroomreservationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventSaveStrategyFactory {

    @Autowired
    private ApplicationContext context;

    public AbstractEventSaveStrategy CreateInstance(Boolean recurrence, Integer pattern){
        AbstractEventSaveStrategy strategy;

        if( recurrence){
            if(pattern != null && pattern == RecurrencePatternEnum.weekly.getPatternCode()){
                strategy = new GenerateWeeklyStrategy();
            }else if(pattern != null && pattern == RecurrencePatternEnum.monthly.getPatternCode()){
                strategy = new GenerateMonthlyStrategy();
            }else{
                throw new RuntimeException("this pattern type is not allow");
            }
        }else{
            strategy = new GenerateNoRecurrenceStrategy();
        }

        strategy.setEventMapper(context.getBean(EventMapper.class));
        strategy.setUserService(context.getBean(UserService.class));
        strategy.setRoomService(context.getBean(RoomService.class));

        return strategy;
    }
}