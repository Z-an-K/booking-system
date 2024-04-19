package com.dxc.meetingroomreservationsystem.service.impl.eventSaveStrategy;

import com.dxc.meetingroomreservationsystem.pojo.Event;
import com.dxc.meetingroomreservationsystem.pojo.EventBo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateNoRecurrenceStrategy extends AbstractEventSaveStrategy{
    @Override
    protected List<Event> generateRecurrenceDate(EventBo event,
                                                 LocalTime startLocalTime,
                                                 LocalTime endLocalTime,
                                                 LocalDate fromDate,
                                                 LocalDate toDate,
                                                 List<List<Date>> recurrenceDate) {
        Event newEvent = new Event(event);
        ArrayList<Event> events = new ArrayList<>();
        events.add(newEvent);
        return events;
    }
}
