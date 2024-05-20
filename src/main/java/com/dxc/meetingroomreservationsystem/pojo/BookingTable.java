package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.border.TitledBorder;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class BookingTable implements Serializable {

    private static final long serialVersionUID = -6244906591625130943L;

    private Date createTime;
    private String roomId;

    private boolean recurrence;

    private String title;

    private String detail;

    private String groupId;

    private String username;

    private Date from;

    private Date to;

    public BookingTable(EventDto eventDto){
        this.createTime = eventDto.getCreateTime();
        this.roomId = eventDto.getRoomId();
        this.recurrence = eventDto.isRecurrence();
        this.title = eventDto.getTitle();
        this.detail = eventDto.getDetails();
        this.username = eventDto.getUsername();
        this.from = eventDto.getFrom();
        this.to = eventDto.getTo();
        this.groupId = eventDto.getGroupId();
    }
}
