package com.dxc.meetingroomreservationsystem.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EventItem implements Serializable {

    private static final long serialVersionUID = -386466611293067513L;
    private char resourceId = '1';
    private String title;
    private Date start;
    private Date end;
    private boolean overlap = true;
    private String backgroundColor;
    private extendedProps extendedProps;

    public EventItem() {
    }

    public EventItem(EventDto event){
        this.title = event.getTitle();
        this.start = event.getStart();
        this.end = event.getEnd();
        this.backgroundColor = event.getBackgroundColor();
        this.extendedProps = new extendedProps.Builder()
                .username(event.getUsername())
                .roomNumber(event.getRoomId())
                .details(event.getDetails())
                .registerTime(event.getCreateTime())
                .location(event.getLocation())
                .recurrence(event.isRecurrence())
                .recurrencePattern(event.getRecurrencePattern())
                .from(event.getFrom())
                .to(event.getTo())
                .week(event.getWeek())
                .weekth(event.getWeekth())
                .weekType(event.getWeekType())
                .build();
    }

}
